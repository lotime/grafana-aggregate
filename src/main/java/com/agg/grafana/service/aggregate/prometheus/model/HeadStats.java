package com.agg.grafana.service.aggregate.prometheus.model;

/**
 * @Author: wzm
 * @Date: 2022/7/15 16:58
 **/
public class HeadStats {

    private Integer numSeries;

    private Integer numLabelPairs;

    private Integer chunkCount;

    public Integer getNumSeries() {
        return numSeries;
    }

    public void setNumSeries(Integer numSeries) {
        this.numSeries = numSeries;
    }

    public Integer getNumLabelPairs() {
        return numLabelPairs;
    }

    public void setNumLabelPairs(Integer numLabelPairs) {
        this.numLabelPairs = numLabelPairs;
    }

    public Integer getChunkCount() {
        return chunkCount;
    }

    public void setChunkCount(Integer chunkCount) {
        this.chunkCount = chunkCount;
    }
}
