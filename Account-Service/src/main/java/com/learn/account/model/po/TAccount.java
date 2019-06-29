package com.learn.account.model.po;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the t_account database table.
 * 
 */
@Entity
@Table(name="t_account")
public class TAccount implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private BigDecimal balance;

	private BigDecimal credit;

	@Column(name="language_code")
	private String languageCode;

	private int status;

	private String timezone;

	private int type;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="utc_created")
	private Date utcCreated;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="utc_modified")
	private Date utcModified;

	//bi-directional many-to-one association to TAccountCompany
	@OneToOne(mappedBy="TAccount")
	private TAccountCompany TAccountCompany;

	//bi-directional many-to-one association to TAccountUser
	@OneToMany(mappedBy="tAccount")
	private List<TAccountUser> TAccountUsers;

	public TAccount() {
	}

	public TAccount setId(int id) {
		this.id = id;
		return this;
	}

	public TAccount setBalance(BigDecimal balance) {
		this.balance = balance;
		return this;
	}

	public TAccount setCredit(BigDecimal credit) {
		this.credit = credit;
		return this;
	}

	public TAccount setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
		return this;
	}

	public TAccount setStatus(int status) {
		this.status = status;
		return this;
	}

	public TAccount setTimezone(String timezone) {
		this.timezone = timezone;
		return this;
	}

	public TAccount setType(int type) {
		this.type = type;
		return this;
	}

	public TAccount setUtcCreated(Date utcCreated) {
		this.utcCreated = utcCreated;
		return this;
	}

	public TAccount setUtcModified(Date utcModified) {
		this.utcModified = utcModified;
		return this;
	}

	public TAccount setTAccountCompanies(TAccountCompany TAccountCompany) {
		this.TAccountCompany = TAccountCompany;
		return this;
	}

	public TAccount setTAccountUsers(List<TAccountUser> TAccountUsers) {
		this.TAccountUsers = TAccountUsers;
		return this;
	}

	public int getId() {
		return this.id;
	}

	public BigDecimal getBalance() {
		return this.balance;
	}

	public BigDecimal getCredit() {
		return this.credit;
	}

	public String getLanguageCode() {
		return this.languageCode;
	}

	public int getStatus() {
		return this.status;
	}

	public String getTimezone() {
		return this.timezone;
	}

	public int getType() {
		return this.type;
	}

	public Date getUtcCreated() {
		return this.utcCreated;
	}

	public Date getUtcModified() {
		return this.utcModified;
	}

	public TAccountCompany getTAccountCompany() {
		return this.TAccountCompany;
	}

	public List<TAccountUser> getTAccountUsers() {
		return this.TAccountUsers;
	}

	public TAccountUser addTAccountUser(TAccountUser TAccountUser) {
		getTAccountUsers().add(TAccountUser);
		TAccountUser.setTAccount(this);

		return TAccountUser;
	}

	public TAccountUser removeTAccountUser(TAccountUser TAccountUser) {
		getTAccountUsers().remove(TAccountUser);
		TAccountUser.setTAccount(null);

		return TAccountUser;
	}

}