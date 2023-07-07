package com.agg.grafana.service.aggregate.prometheus.model;

/**
 * @Author: wzm
 * @Date: 2022/7/15 14:05
 **/
public class Response<T> {

    private String status;

    private T data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
