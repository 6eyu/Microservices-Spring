package com.learn.oauth2.model.po;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "plugins")
public class TPlugin implements Serializable {

    private static final long serialVersionUID = 1L;

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
    private List<TSubscription> TSubscriptions;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "title", nullable = false, length = 100)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "fee", nullable = false, precision = 0)
    public Float getFee() {
        return fee;
    }

    public void setFee(Float fee) {
        this.fee = fee;
    }

    @Basic
    @Column(name = "description", nullable = false, length = 65535, columnDefinition="LONGTEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "detail_description", nullable = false, length = 65535, columnDefinition="TEXT")
    public String getDetailDescription() {
        return detailDescription;
    }

    public void setDetailDescription(String detailDescription) {
        this.detailDescription = detailDescription;
    }

    @Basic
    @Column(name = "url", nullable = false, length = 50)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "category_id", nullable = false)
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @Basic
    @Column(name = "visible", nullable = false)
    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
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
    @Column(name = "status", nullable = false)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "trial_period", nullable = true)
    public Integer getTrialPeriod() {
        return trialPeriod;
    }

    public void setTrialPeriod(Integer trialPeriod) {
        this.trialPeriod = trialPeriod;
    }

    @Basic
    @Column(name = "sub_category", nullable = true)
    public Integer getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(Integer subCategory) {
        this.subCategory = subCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TPlugin that = (TPlugin) o;
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

    @OneToMany(mappedBy = "tPlugin")
    public List<TSubscription> getTSubscriptions() {
        return TSubscriptions;
    }

    public void setTSubscriptions(List<TSubscription> TSubscriptions) {
        this.TSubscriptions = TSubscriptions;
    }
}
