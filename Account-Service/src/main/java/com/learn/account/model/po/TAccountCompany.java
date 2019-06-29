package com.learn.account.model.po;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;


/**
 * The persistent class for the t_account_company database table.
 * 
 */
@Entity
@Table(name="t_account_company")
public class TAccountCompany implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int id;

	private String abn;

	@Column(name="company_address1")
	private String companyAddress1;

	@Column(name="company_address2")
	private String companyAddress2;

	@Column(name="company_country")
	private String companyCountry;

	@Column(name="company_name")
	private String companyName;

	@Column(name="company_postcode")
	private String companyPostcode;

	@Column(name="company_state")
	private String companyState;

	@Column(name="company_suburb")
	private String companySuburb;

	private String email;

	private String phone;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="utc_created")
	private Date utcCreated;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="utc_modified")
	private Date utcModified;

	//bi-directional many-to-one association to TAccount
	@OneToOne
	@JoinColumn(name="account_id")
	private TAccount TAccount;

	public TAccountCompany() {
		
	}

	public TAccountCompany setId(int id) {
		this.id = id;
		return this;
	}

	public TAccountCompany setAbn(String abn) {
		this.abn = abn;
		return this;
	}

	public TAccountCompany setCompanyAddress1(String companyAddress1) {
		this.companyAddress1 = companyAddress1;
		return this;
	}

	public TAccountCompany setCompanyAddress2(String companyAddress2) {
		this.companyAddress2 = companyAddress2;
		return this;
	}

	public TAccountCompany setCompanyCountry(String companyCountry) {
		this.companyCountry = companyCountry;
		return this;
	}

	public TAccountCompany setCompanyName(String companyName) {
		this.companyName = companyName;
		return this;
	}

	public TAccountCompany setCompanyPostcode(String companyPostcode) {
		this.companyPostcode = companyPostcode;
		return this;
	}

	public TAccountCompany setCompanyState(String companyState) {
		this.companyState = companyState;
		return this;
	}

	public TAccountCompany setCompanySuburb(String companySuburb) {
		this.companySuburb = companySuburb;
		return this;
	}

	public TAccountCompany setEmail(String email) {
		this.email = email;
		return this;
	}

	public TAccountCompany setPhone(String phone) {
		this.phone = phone;
		return this;
	}

	public TAccountCompany setUtcCreated(Date utcCreated) {
		this.utcCreated = utcCreated;
		return this;
	}

	public TAccountCompany setUtcModified(Date utcModified) {
		this.utcModified = utcModified;
		return this;
	}

	public TAccountCompany setTAccount(TAccount TAccount) {
		this.TAccount = TAccount;
		return this;
	}

	public int getId() {
		return this.id;
	}

	public String getAbn() {
		return this.abn;
	}

	public String getCompanyAddress1() {
		return this.companyAddress1;
	}

	public String getCompanyAddress2() {
		return this.companyAddress2;
	}

	public String getCompanyCountry() {
		return this.companyCountry;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public String getCompanyPostcode() {
		return this.companyPostcode;
	}

	public String getCompanyState() {
		return this.companyState;
	}

	public String getCompanySuburb() {
		return this.companySuburb;
	}

	public String getEmail() {
		return this.email;
	}

	public String getPhone() {
		return this.phone;
	}

	public Date getUtcCreated() {
		return this.utcCreated;
	}

	public Date getUtcModified() {
		return this.utcModified;
	}

	@JsonIgnore
	public TAccount getTAccount() {
		return this.TAccount;
	}

}