package com.agg.grafana.service.aggregate.grafana;

import com.agg.grafana.service.aggregate.grafana.model.Alert;
import com.agg.grafana.service.aggregate.grafana.model.Dashboard;
import com.agg.grafana.service.aggregate.grafana.model.Folder;
import com.agg.grafana.service.aggregate.grafana.model.Settings;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;
import java.util.List;

/**
 * @Author: wzm
 * @Date: 2022/7/15 10:23
 **/
@FeignClient(value = "grafanaApiClient",url = "empty")
public interface GrafanaApiClient {

    /**
     * 获取grafana所有图表信息
     * https://grafana.com/docs/grafana/latest/developers/http_api/folder_dashboard_search/
     * @param uri
     * @param type
     * @return
     */
    @GetMapping("/api/search")
    List<Dashboard> grafanaSearch(URI uri, @RequestParam(value = "type",required = false)String type);


    /**
     * 获取所有告警
     * @param uri
     * @return
     */
    @GetMapping("/api/alerts")
    List<Alert> grafanaAlertList(URI uri);


    /**
     * 获取所有文件夹
     * @param uri
     * @return
     */
    @GetMapping("/api/folders")
    List<Folder> grafanaFolderList(URI uri);


    /**
     * 获取grafana配置信息
     * @param uri
     * @return
     */
    @GetMapping("/api/frontend/settings")
    Settings grafanaSettings(URI uri);

}
