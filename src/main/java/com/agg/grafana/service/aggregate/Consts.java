package com.agg.grafana.service.aggregate;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: wzm
 * @Date: 2022/7/15 14:54
 **/
public interface Consts {

    String DEFAULT_FOLDER = "General";

    String SEARCH_TYPE_FOLDER = "dash-folder";
    String SEARCH_TYPE_DASHBOARD = "dash-db";

    String HTTP_PREFIX = "http://";

    String ALERT_STATE_OK = "ok";
    List<String> ALERT_STATE_NOT_OK = Arrays.asList("pending","no_data","alerting");
    String ALERT_STATE_PAUSE = "paused";
}
