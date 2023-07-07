package com.agg.grafana.service.aggregate.grafana.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wzm
 * @Date: 2022/7/15 13:59
 **/
public class Folder {

    private Integer id;

    private String uid;

    private String title;


    public Folder() {
    }

    public Folder(String title) {
        this.title = title;
    }

    private List<Dashboard> dashboardList;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Dashboard> getDashboardList() {
        return dashboardList;
    }

    public void setDashboardList(List<Dashboard> dashboardList) {
        this.dashboardList = dashboardList;
    }

    public void addDashboard(Dashboard dashboard){
        if (this.dashboardList == null) {
            this.dashboardList = new ArrayList<>();
        }
        this.dashboardList.add(dashboard);
    }
}
