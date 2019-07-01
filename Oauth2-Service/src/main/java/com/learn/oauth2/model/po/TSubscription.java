package com.learn.oauth2.model.po;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "account_plugin")
public class TSubscription implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private transient Timestamp createdAt;
    private transient Timestamp updatedAt;
    private String settings;
    private transient Timestamp expiryAt;
    private Integer status;
    private transient Integer autoRenew;
    private Integer planId;
    private transient String limitedRecord;
    private TAccount tAccount;
    private TPlugin tPlugin;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "created_at", nullable = true)
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Basic
    @Column(name = "updated_at", nullable = true)
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Basic
    @Column(name = "settings", nullable = false, length = 65535, columnDefinition="TEXT")
    public String getSettings() {
        return settings;
    }

    public void setSettings(String settings) {
        this.settings = settings;
    }

    @Basic
    @Column(name = "expiry_at", nullable = true)
    public Timestamp getExpiryAt() {
        return expiryAt;
    }

    public void setExpiryAt(Timestamp expiryAt) {
        this.expiryAt = expiryAt;
    }

    @Basic
    @Column(name = "status", nullable = true)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "auto_renew", nullable = true)
    public Integer getAutoRenew() {
        return autoRenew;
    }

    public void setAutoRenew(Integer autoRenew) {
        this.autoRenew = autoRenew;
    }

    @Basic
    @Column(name = "plan_id", nullable = true)
    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    @Basic
    @JsonIgnore
    @Column(name = "limited_record", nullable = true, length = 65535, columnDefinition="TEXT")
    public String getLimitedRecord() {
        return limitedRecord;
    }

    public void setLimitedRecord(String limitedRecord) {
        this.limitedRecord = limitedRecord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TSubscription that = (TSubscription) o;
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

//    @ManyToOne
//    @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
//    public TAccount getTAccount() {
//        return TAccount;
//    }
//
//    public void setTAccount(TAccount TAccount) {
//        this.TAccount = TAccount;
//    }
//
//    @ManyToOne
//    @JoinColumn(name = "plugin_id", referencedColumnName = "id", nullable = false)
//    public TPlugin getTPlugin() {
//        return TPlugin;
//    }
//
//    public void setTPlugin(TPlugin TPlugin) {
//        this.TPlugin = TPlugin;
//    }

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
    public TAccount gettAccount() {
        return tAccount;
    }

    public void settAccount(TAccount tAccount) {
        this.tAccount = tAccount;
    }

    @ManyToOne
    @JoinColumn(name = "plugin_id", referencedColumnName = "id", nullable = false)
    public TPlugin gettPlugin() {
        return tPlugin;
    }

    public void settPlugin(TPlugin tPlugin) {
        this.tPlugin = tPlugin;
    }
}

