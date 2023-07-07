package com.agg.grafana.service.aggregate;


import com.agg.grafana.service.aggregate.prometheus.model.HeadStats;

/**
 * @Author: wzm
 * @Date: 2022/7/15 17:00
 **/
public class PrometheusInfo {

    private String address;

    private Integer jobCount;

    private Integer upCount;

    private Integer downCount;

    private String version;

    private HeadStats tsdbHeadStats;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getJobCount() {
        return jobCount;
    }

    public void setJobCount(Integer jobCount) {
        this.jobCount = jobCount;
    }

    public Integer getUpCount() {
        return upCount;
    }

    public void setUpCount(Integer upCount) {
        this.upCount = upCount;
    }

    public Integer getDownCount() {
        return downCount;
    }

    public void setDownCount(Integer downCount) {
        this.downCount = downCount;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public HeadStats getTsdbHeadStats() {
        return tsdbHeadStats;
    }

    public void setTsdbHeadStats(HeadStats tsdbHeadStats) {
        this.tsdbHeadStats = tsdbHeadStats;
    }
}
