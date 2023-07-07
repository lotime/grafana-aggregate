package com.agg.grafana.service.aggregate.prometheus;

import com.agg.grafana.service.aggregate.prometheus.model.BuildData;
import com.agg.grafana.service.aggregate.prometheus.model.QueryData;
import com.agg.grafana.service.aggregate.prometheus.model.Response;
import com.agg.grafana.service.aggregate.prometheus.model.TsdbData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;

/**
 * @Author: wzm
 * @Date: 2022/7/15 10:23
 **/
@FeignClient(value = "prometheusApiClient",url = "empty")
public interface PrometheusApiClient {

    /**
     * 查看prometheus数据
     * @param uri
     * @param query
     * @return
     */
    @GetMapping("/api/v1/query")
    Response<QueryData> search(URI uri, @RequestParam("query")String query);


    /**
     * 构建信息
     * @param uri
     * @return
     */
    @GetMapping("/api/v1/status/buildinfo")
    Response<BuildData> buildInfo(URI uri);


    /**
     * TSDB信息
     * @param uri
     * @return
     */
    @GetMapping("/api/v1/status/tsdb")
    Response<TsdbData> tsdbState(URI uri);

}
