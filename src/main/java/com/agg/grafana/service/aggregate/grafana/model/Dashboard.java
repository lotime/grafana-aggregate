package com.agg.grafana.service.aggregate.grafana.model;

import com.agg.grafana.service.aggregate.Consts;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * grafana dashboard信息
 * @Author: wzm
 * @Date: 2022/7/15 11:21
 **/
public class Dashboard {

    private Integer id;

    /**
     * 排序序号
     */
    private Integer sortMeta;

    /**
     * 标签列表
     */
    private List<String> tags;

    /**
     * 图表标题
     */
    private String title;

    /**
     * 类型
     */
    private String type;

    private String uid;

    private String uri;

    /**
     * dashboard url
     */
    private String url;

    /**
     * 目录信息，若在General下则无
     */
    private Integer folderId;
    private String folderUid;
    private String folderTitle;
    private String folderUrl;


    /**
     * 额外添加告警信息
     */
    private List<Alert> alertList;

    /**
     * ok,not_ok,pause
     */
    private String mostSeriousAlertState;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSortMeta() {
        return sortMeta;
    }

    public void setSortMeta(Integer sortMeta) {
        this.sortMeta = sortMeta;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public Integer getFolderId() {
        return folderId;
    }

    public void setFolderId(Integer folderId) {
        this.folderId = folderId;
    }

    public String getFolderUid() {
        return folderUid;
    }

    public void setFolderUid(String folderUid) {
        this.folderUid = folderUid;
    }

    public String getFolderTitle() {
        return folderTitle;
    }

    public void setFolderTitle(String folderTitle) {
        this.folderTitle = folderTitle;
    }

    public String getFolderUrl() {
        return folderUrl;
    }

    public void setFolderUrl(String folderUrl) {
        this.folderUrl = folderUrl;
    }

    public List<Alert> getAlertList() {
        return alertList;
    }

    public void setAlertList(List<Alert> alertList) {
        this.alertList = alertList;
    }

    public void addAlerts(List<Alert> alerts){
        if (alerts == null || alerts.isEmpty()) {
            return;
        }
        if (this.alertList == null) {
            this.alertList = new ArrayList<>();
        }
        this.alertList.addAll(alerts);
        alerts.forEach(alert -> {
            if (Consts.ALERT_STATE_NOT_OK.contains(alert.getState())) {
                mostSeriousAlertState = "not_ok";
                return;
            }
            if ((StringUtils.isEmpty(mostSeriousAlertState) || "pasuse".contains(mostSeriousAlertState)) && Consts.ALERT_STATE_OK.equals(alert.getState())) {
                mostSeriousAlertState = "ok";
                return;
            }
            if (StringUtils.isEmpty(mostSeriousAlertState) && Consts.ALERT_STATE_PAUSE.equals(alert.getState())) {
                mostSeriousAlertState = "pause";
            }
        });
    }

    public String getMostSeriousAlertState() {
        return mostSeriousAlertState;
    }

    public void setMostSeriousAlertState(String mostSeriousAlertState) {
        this.mostSeriousAlertState = mostSeriousAlertState;
    }
}
