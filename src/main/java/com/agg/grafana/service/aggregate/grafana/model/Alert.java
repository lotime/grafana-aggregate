package com.agg.grafana.service.aggregate.grafana.model;

import java.util.Date;

/**
 * @Author: wzm
 * @Date: 2022/7/15 13:38
 **/
public class Alert {

    private Integer id;

    private String name;

    private Date newStateDate;

    private Integer panelId;

    private String state;

    private String url;

    private Integer dashboardId;

    private String dashboardSlug;

    private String dashboardUid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getNewStateDate() {
        return newStateDate;
    }

    public void setNewStateDate(Date newStateDate) {
        this.newStateDate = newStateDate;
    }

    public Integer getPanelId() {
        return panelId;
    }

    public void setPanelId(Integer panelId) {
        this.panelId = panelId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getDashboardId() {
        return dashboardId;
    }

    public void setDashboardId(Integer dashboardId) {
        this.dashboardId = dashboardId;
    }

    public String getDashboardSlug() {
        return dashboardSlug;
    }

    public void setDashboardSlug(String dashboardSlug) {
        this.dashboardSlug = dashboardSlug;
    }

    public String getDashboardUid() {
        return dashboardUid;
    }

    public void setDashboardUid(String dashboardUid) {
        this.dashboardUid = dashboardUid;
    }
}
