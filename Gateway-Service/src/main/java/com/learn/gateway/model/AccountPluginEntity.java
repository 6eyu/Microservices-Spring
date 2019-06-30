package com.learn.gateway.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Data
public class AccountPluginEntity implements Serializable {
    private Integer id;
    private transient Timestamp createdAt;
    private transient Timestamp updatedAt;
    private String settings;
    private transient Timestamp expiryAt;
    private Integer status;
    private transient Integer autoRenew;
    private Integer planId;
    private transient String limitedRecord;
    @JsonIgnore
    private AccountsEntity accountEntity;
    private PluginsEntity pluginEntity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountPluginEntity that = (AccountPluginEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt) &&
                Objects.equals(settings, that.settings) &&
                Objects.equals(expiryAt, that.expiryAt) &&
                Objects.equals(status, that.status) &&
                Objects.equals(autoRenew, that.autoRenew) &&
                Objects.equals(planId, that.planId) &&
                Objects.equals(limitedRecord, that.limitedRecord);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, updatedAt, settings, expiryAt, status, autoRenew, planId, limitedRecord);
    }

}
