package com.agg.grafana.service.aggregate.prometheus.model;

/**
 * @Author: wzm
 * @Date: 2022/7/15 16:58
 **/
public class TsdbData {

    private HeadStats headStats;

    public HeadStats getHeadStats() {
        return headStats;
    }

    public void setHeadStats(HeadStats headStats) {
        this.headStats = headStats;
    }
}
