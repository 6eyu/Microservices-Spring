package com.learn.oauth2.model.dto;

import lombok.Data;

@Data
public class SubscriptionDTO {
    private Integer id;
    private Integer account_id;
    private String settings;
    private Integer status;
    private PluginDTO pluginDto;
}
