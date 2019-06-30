package com.learn.gateway.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Data
public class PluginsEntity implements Serializable{
    private Integer id;
    private String name;
    private String title;
    private Float fee;
    private String description;
    private String detailDescription;
    private String url;
    private Integer categoryId;
    private Boolean visible;
    private String settings;
    private Integer status;
    private Integer trialPeriod;
    private Integer subCategory;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PluginsEntity that = (PluginsEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(title, that.title) &&
                Objects.equals(fee, that.fee) &&
                Objects.equals(description, that.description) &&
                Objects.equals(detailDescription, that.detailDescription) &&
                Objects.equals(url, that.url) &&
                Objects.equals(categoryId, that.categoryId) &&
                Objects.equals(visible, that.visible) &&
                Objects.equals(settings, that.settings) &&
                Objects.equals(status, that.status) &&
                Objects.equals(trialPeriod, that.trialPeriod) &&
                Objects.equals(subCategory, that.subCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, title, fee, description, detailDescription, url, categoryId, visible, settings, status, trialPeriod, subCategory);
    }
}
