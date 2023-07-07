package com.agg.grafana.service.aggregate;

import com.agg.grafana.service.aggregate.grafana.model.Folder;

import java.util.List;

/**
 * @Author: wzm
 * @Date: 2022/7/15 15:42
 **/
public class GrafanaInfo {

    private String name;

    private String address;

    private String proxyAddress;

    private String version;

    private List<Folder> folderList;

    private Integer dashboardCount;

    private Integer alertOkCount = 0;
    private Integer alertNotOkCount = 0;
    private Integer alertPauseCount = 0;


    /**
     * 自定义
     */
    private String uid;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        this.setUid(String.valueOf(address.hashCode()));
    }

    public String getProxyAddress() {
        return proxyAddress;
    }

    public void setProxyAddress(String proxyAddress) {
        this.proxyAddress = proxyAddress;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<Folder> getFolderList() {
        return folderList;
    }

    public void setFolderList(List<Folder> folderList) {
        this.folderList = folderList;
    }

    public Integer getDashboardCount() {
        return dashboardCount;
    }

    public void setDashboardCount(Integer dashboardCount) {
        this.dashboardCount = dashboardCount;
    }

    public Integer getAlertOkCount() {
        return alertOkCount;
    }

    public void setAlertOkCount(Integer alertOkCount) {
        this.alertOkCount = alertOkCount;
    }

    public Integer getAlertNotOkCount() {
        return alertNotOkCount;
    }

    public void setAlertNotOkCount(Integer alertNotOkCount) {
        this.alertNotOkCount = alertNotOkCount;
    }

    public Integer getAlertPauseCount() {
        return alertPauseCount;
    }

    public void setAlertPauseCount(Integer alertPauseCount) {
        this.alertPauseCount = alertPauseCount;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void addAlertOkCount(int alertNotOkCount){
        this.alertOkCount += alertNotOkCount;
    }
    public void addAlertNotOkCount(int alertNotOkCount) {
        this.alertNotOkCount += alertNotOkCount;
    }
    public void addAlertPauseCount(int alertPauseCount) {
        this.alertPauseCount += alertPauseCount;
    }
}
