package com.learn.account.model.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AccountCompanyDTO {

	
	@Digits(integer=10, fraction = 0)
	private int id;
	
	@Size(min= 10, max=50, message="ABN must include 11 digits")
	private String abn;
	
	@NotNull
	@Size(min= 1, max=200, message="company must have a name")
	private String companyName;
	
	@NotNull
	@Size(min=4, max=100, message="company must have a valid email")
	private String email;
	
	@Size(min=1, max=30)
	private String phone;
	
	@NotNull
	@Size(min=1, max=255)
	private String companyAddress1;
	
	@Size(min=0, max=255)
	private String companyAddress2;
	
	@NotNull
	@Size(min=1, max=100)
	private String companySuburb;
	
	@NotNull
	@Size(min=1,max=50)
	private String companyState;
	
	@NotNull
	@Size(min=1, max=50)
	private String companyCountry;
	
	@NotNull
	@Size(min=1, max=45)
	private String companyPostcode;
	
//	@NotNull
//	private TAccountDTO TAccount;

	public AccountCompanyDTO setId(int id) {
		this.id = id;
		return this;
	}

	public AccountCompanyDTO setAbn(String abn) {
		this.abn = abn;
		return this;
	}

	public AccountCompanyDTO setCompanyName(String companyName) {
		this.companyName = companyName;
		return this;
	}

	public AccountCompanyDTO setEmail(String email) {
		this.email = email;
		return this;
	}

	public AccountCompanyDTO setPhone(String phone) {
		this.phone = phone;
		return this;
	}

	public AccountCompanyDTO setCompanyAddress1(String companyAddress1) {
		this.companyAddress1 = companyAddress1;
		return this;
	}

	public AccountCompanyDTO setCompanyAddress2(String companyAddress2) {
		this.companyAddress2 = companyAddress2;
		return this;
	}

	public AccountCompanyDTO setCompanySuburb(String companySuburb) {
		this.companySuburb = companySuburb;
		return this;
	}

	public AccountCompanyDTO setCompanyState(String companyState) {
		this.companyState = companyState;
		return this;
	}

	public AccountCompanyDTO setCompanyCountry(String companyCountry) {
		this.companyCountry = companyCountry;
		return this;
	}

	public AccountCompanyDTO setCompanyPostcode(String companyPostcode) {
		this.companyPostcode = companyPostcode;
		return this;
	}

//	public TAccountCompanyDTO setTAccount(TAccountDTO tAccount) {
//		this.TAccount = tAccount;
//		return this;
//	}

	public int getId() {
		return id;
	}

	public String getAbn() {
		return abn;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public String getCompanyAddress1() {
		return companyAddress1;
	}

	public String getCompanyAddress2() {
		return companyAddress2;
	}

	public String getCompanySuburb() {
		return companySuburb;
	}

	public String getCompanyState() {
		return companyState;
	}

	public String getCompanyCountry() {
		return companyCountry;
	}

	public String getCompanyPostcode() {
		return companyPostcode;
	}

//	public TAccountDTO getTAccount() {
//		return TAccount;
//	}

	
	
	
}
