package com.agg.grafana.service.aggregate.grafana.model;

/**
 * @Author: wzm
 * @Date: 2022/7/15 15:51
 **/
public class Settings {

    private String appSubUrl;

    private BuildInfo buildInfo;

    public String getAppSubUrl() {
        return appSubUrl;
    }

    public void setAppSubUrl(String appSubUrl) {
        this.appSubUrl = appSubUrl;
    }

    public BuildInfo getBuildInfo() {
        return buildInfo;
    }

    public void setBuildInfo(BuildInfo buildInfo) {
        this.buildInfo = buildInfo;
    }
}
