package com.agg.grafana.service.aggregate;


import com.agg.grafana.dao.Host;
import com.agg.grafana.dao.HostMapper;
import com.agg.grafana.service.aggregate.grafana.GrafanaApiClient;
import com.agg.grafana.service.aggregate.grafana.model.Alert;
import com.agg.grafana.service.aggregate.grafana.model.Dashboard;
import com.agg.grafana.service.aggregate.grafana.model.Folder;
import com.agg.grafana.service.aggregate.grafana.model.Settings;
import com.agg.grafana.service.aggregate.prometheus.PrometheusApiClient;
import com.agg.grafana.service.aggregate.prometheus.model.BuildData;
import com.agg.grafana.service.aggregate.prometheus.model.QueryData;
import com.agg.grafana.service.aggregate.prometheus.model.Response;
import com.agg.grafana.service.aggregate.prometheus.model.TsdbData;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.agg.grafana.service.aggregate.Consts.*;

/**
 * @Author: wzm
 * @Date: 2022/7/15 14:29
 **/
@Service
public class ProGraMonitorService {
    private static final Logger LOG = LoggerFactory.getLogger(ProGraMonitorService.class);

    @Autowired
    private HostMapper hostMapper;

    @Autowired
    private GrafanaApiClient grafanaApiClient;

    @Autowired
    private PrometheusApiClient prometheusApiClient;

    public List<GrafanaInfo> getAllGrafanaInfo() throws URISyntaxException {
        QueryWrapper<Host> hostWrapper = new QueryWrapper<>();
        hostWrapper.eq("type","Grafana");
        List<Host> hostList = hostMapper.selectList(hostWrapper);
        List<String> grafanaAddressList = hostList.stream().map(Host::getAddress).collect(Collectors.toList());

        List<GrafanaInfo> grafanaList = new ArrayList<>();
        for (String host : grafanaAddressList) {
            URI uri = new URI(HTTP_PREFIX + host);
            Settings settings = this.grafanaApiClient.grafanaSettings(uri);
            GrafanaInfo grafana = new GrafanaInfo();
            grafana.setAddress(HTTP_PREFIX + host);
            grafana.setProxyAddress(grafana.getAddress());
            grafana.setName(settings.getAppSubUrl());
            grafana.setVersion(settings.getBuildInfo().getVersion());

            List<Folder> folderList = this.grafanaApiClient.grafanaFolderList(uri);
            grafana.setFolderList(folderList);
            folderList.add(new Folder(DEFAULT_FOLDER));
            Map<String, Folder> folderNameMap = folderList.stream().collect(Collectors.toMap(Folder::getTitle, Function.identity()));

            List<Alert> alertList = this.grafanaApiClient.grafanaAlertList(uri);
            Map<String, List<Alert>> dashAlertMap = new HashMap<>();
            for (Alert alert : alertList) {
                List<Alert> list = dashAlertMap.getOrDefault(alert.getDashboardUid(),new ArrayList<>());
                list.add(alert);
                dashAlertMap.put(alert.getDashboardUid(),list);
            }

            List<Dashboard> dashboardList = this.grafanaApiClient.grafanaSearch(uri,SEARCH_TYPE_DASHBOARD);
            for (Dashboard dashboard : dashboardList) {
                dashboard.setUrl(HTTP_PREFIX + host + dashboard.getUrl());

                List<Alert> alerts = dashAlertMap.get(dashboard.getUid());
                if (!CollectionUtils.isEmpty(alerts)) {
                    alerts.forEach(alert -> {
                        alert.setUrl(HTTP_PREFIX + host + alert.getUrl());
                        if (ALERT_STATE_OK.equals(alert.getState())) {
                            grafana.addAlertOkCount(1);
                        }else if (ALERT_STATE_PAUSE.equals(alert.getState())) {
                            grafana.addAlertPauseCount(1);
                        }else{
                            grafana.addAlertNotOkCount(1);
                        }
                    });
                    dashboard.addAlerts(alerts);
                }

                Folder folder;
                if (Objects.isNull(dashboard.getFolderTitle())) {
                    folder = folderNameMap.get(DEFAULT_FOLDER);
                } else {
                    folder = folderNameMap.get(dashboard.getFolderTitle());
                }
                folder.addDashboard(dashboard);
            }
            grafana.setDashboardCount(dashboardList.size());
            grafanaList.add(grafana);
        }
        return grafanaList;
    }


    public List<PrometheusInfo> getAllPrometheusInfo() {
        QueryWrapper<Host> hostWrapper = new QueryWrapper<>();
        hostWrapper.eq("type","Prometheus");
        List<Host> hostList = hostMapper.selectList(hostWrapper);
        List<String> prometheusAddressList = hostList.stream().map(Host::getAddress).collect(Collectors.toList());

        List<PrometheusInfo> prometheusList = new ArrayList<>();
        try {
            for (String host : prometheusAddressList) {
                URI uri = new URI(HTTP_PREFIX + host);
                PrometheusInfo prometheusInfo = new PrometheusInfo();
                prometheusInfo.setAddress(HTTP_PREFIX + host);
                Response<BuildData> buildDataResponse = this.prometheusApiClient.buildInfo(uri);
                prometheusInfo.setVersion(buildDataResponse.getData().getVersion());

                Response<QueryData> downSearchResponse = this.prometheusApiClient.search(uri, "count(up==0)");
                if (CollectionUtils.isEmpty(downSearchResponse.getData().getResult()) || CollectionUtils.isEmpty(downSearchResponse.getData().getResult().get(0).getValue())) {
                    prometheusInfo.setDownCount(0);
                } else {
                    prometheusInfo.setDownCount(Integer.parseInt(downSearchResponse.getData().getResult().get(0).getValue().get(1).toString()));
                }

                Response<QueryData> upSearchResponse = this.prometheusApiClient.search(uri, "count(up==1)");
                if (CollectionUtils.isEmpty(upSearchResponse.getData().getResult()) || CollectionUtils.isEmpty(upSearchResponse.getData().getResult().get(0).getValue())) {
                    prometheusInfo.setUpCount(0);
                } else {
                    prometheusInfo.setUpCount(Integer.parseInt(upSearchResponse.getData().getResult().get(0).getValue().get(1).toString()));
                }

                prometheusInfo.setJobCount(prometheusInfo.getDownCount() + prometheusInfo.getUpCount());

                Response<TsdbData> tsdbDataResponse = this.prometheusApiClient.tsdbState(uri);
                prometheusInfo.setTsdbHeadStats(tsdbDataResponse.getData().getHeadStats());

                prometheusList.add(prometheusInfo);
            }
        }catch (Exception e){
            LOG.error("get prometheus info error",e);
        }
        return prometheusList;
    }
}
