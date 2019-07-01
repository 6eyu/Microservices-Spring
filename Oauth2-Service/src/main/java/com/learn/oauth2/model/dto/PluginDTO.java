package com.learn.oauth2.model.dto;

import lombok.Data;

@Data
public class PluginDTO {
    private Integer id;
    private String name;
    private String title;
    private String settings;
    private Integer status;
}
