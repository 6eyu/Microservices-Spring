package com.learn.gateway.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Data
public class AccountsEntity implements Serializable{
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
    private Collection<UsersEntity> usersEntities;
    private Collection<AccountPluginEntity> accountPluginEntities;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountsEntity that = (AccountsEntity) o;
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
}
