package com.agg.grafana.service.aggregate.prometheus.model;

import java.util.List;
import java.util.Map;

/**
 * @Author: wzm
 * @Date: 2022/7/15 14:06
 **/
public class Result {

    private Map<String, String> metric;

    private List<Object> value;

    public Map<String, String> getMetric() {
        return metric;
    }

    public void setMetric(Map<String, String> metric) {
        this.metric = metric;
    }

    public List<Object> getValue() {
        return value;
    }

    public void setValue(List<Object> value) {
        this.value = value;
    }
}
