package com.learn.oauth2.model.po;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "accounts", schema = "eiz_test")
public class TAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String email;
    private String companyName;
    private String abn;
    private String phone;
    private String address;
    private String status;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Timestamp deletedAt;
    private String companyAddress1;
    private String companyAddress2;
    private String companySuburb;
    private String companyState;
    private String companyCountry;
    private Integer companyPostcode;
    private Integer type;
    private String referenceNum;
    private BigDecimal credit;
    private BigDecimal balance;
    @JsonIgnore
    private List<TUser> TUsers;
    @JsonIgnore
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
    @Column(name = "email", nullable = false, length = 200)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "companyName", nullable = false, length = 50)
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Basic
    @Column(name = "ABN", nullable = false, length = 20)
    public String getAbn() {
        return abn;
    }

    public void setAbn(String abn) {
        this.abn = abn;
    }

    @Basic
    @Column(name = "phone", nullable = true, length = 20)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "address", nullable = false, length = 255)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "status", nullable = false, length = 10)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
    @Column(name = "deleted_at", nullable = true)
    public Timestamp getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Timestamp deletedAt) {
        this.deletedAt = deletedAt;
    }

    @Basic
    @Column(name = "company_address1", nullable = false, length = 255)
    public String getCompanyAddress1() {
        return companyAddress1;
    }

    public void setCompanyAddress1(String companyAddress1) {
        this.companyAddress1 = companyAddress1;
    }

    @Basic
    @Column(name = "company_address2", nullable = true, length = 255)
    public String getCompanyAddress2() {
        return companyAddress2;
    }

    public void setCompanyAddress2(String companyAddress2) {
        this.companyAddress2 = companyAddress2;
    }

    @Basic
    @Column(name = "company_suburb", nullable = false, length = 255)
    public String getCompanySuburb() {
        return companySuburb;
    }

    public void setCompanySuburb(String companySuburb) {
        this.companySuburb = companySuburb;
    }

    @Basic
    @Column(name = "company_state", nullable = false, length = 255)
    public String getCompanyState() {
        return companyState;
    }

    public void setCompanyState(String companyState) {
        this.companyState = companyState;
    }

    @Basic
    @Column(name = "company_country", nullable = false, length = 255)
    public String getCompanyCountry() {
        return companyCountry;
    }

    public void setCompanyCountry(String companyCountry) {
        this.companyCountry = companyCountry;
    }

    @Basic
    @Column(name = "company_postcode", nullable = false)
    public Integer getCompanyPostcode() {
        return companyPostcode;
    }

    public void setCompanyPostcode(Integer companyPostcode) {
        this.companyPostcode = companyPostcode;
    }

    @Basic
    @Column(name = "type", nullable = false)
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "reference_num", nullable = false, length = 45)
    public String getReferenceNum() {
        return referenceNum;
    }

    public void setReferenceNum(String referenceNum) {
        this.referenceNum = referenceNum;
    }

    @Basic
    @Column(name = "credit", nullable = true, precision = 2)
    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    @Basic
    @Column(name = "balance", nullable = true, precision = 2)
    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TAccount that = (TAccount) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(email, that.email) &&
                Objects.equals(companyName, that.companyName) &&
                Objects.equals(abn, that.abn) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(address, that.address) &&
                Objects.equals(status, that.status) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt) &&
                Objects.equals(deletedAt, that.deletedAt) &&
                Objects.equals(companyAddress1, that.companyAddress1) &&
                Objects.equals(companyAddress2, that.companyAddress2) &&
                Objects.equals(companySuburb, that.companySuburb) &&
                Objects.equals(companyState, that.companyState) &&
                Objects.equals(companyCountry, that.companyCountry) &&
                Objects.equals(companyPostcode, that.companyPostcode) &&
                Objects.equals(type, that.type) &&
                Objects.equals(referenceNum, that.referenceNum) &&
                Objects.equals(credit, that.credit) &&
                Objects.equals(balance, that.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, companyName, abn, phone, address, status, createdAt, updatedAt, deletedAt, companyAddress1, companyAddress2, companySuburb, companyState, companyCountry, companyPostcode, type, referenceNum, credit, balance);
    }

    @OneToMany(mappedBy = "TAccount", fetch = FetchType.LAZY)
    public List<TUser> getTUsers() {
        return TUsers;
    }

    public void setTUsers(List<TUser> TUsers) {
        this.TUsers = TUsers;
    }

    @OneToMany(mappedBy = "tAccount", fetch = FetchType.EAGER)
    public List<TSubscription> getTSubscriptions() {
        return TSubscriptions;
    }

    public void setTSubscriptions(List<TSubscription> TSubscriptions) {
        this.TSubscriptions = TSubscriptions;
    }
}
