package com.agg.grafana.service.aggregate.prometheus.model;

import java.util.List;

/**
 * @Author: wzm
 * @Date: 2022/7/15 14:05
 **/
public class QueryData {

    private String  resultType;

    private List<Result> result;

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }
}
