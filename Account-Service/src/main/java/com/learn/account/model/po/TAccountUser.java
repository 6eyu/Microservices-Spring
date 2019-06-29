package com.learn.account.model.po;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;


/**
 * The persistent class for the t_account_user database table.
 * 
 */
@Entity
@Table(name="t_account_user")
public class TAccountUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String email;

	private String name;

	private String password;

	@Column(name="register_hash")
	private String registerHash;

	private int status;

	private String username;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="utc_created")
	private Date utcCreated;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="utc_modified")
	private Date utcModified;

	//bi-directional many-to-one association to TAccount
	@JoinColumn(name="account_id")
	@ManyToOne
	private TAccount tAccount;


	public TAccountUser setId(Integer id) {
		this.id = id;
		return this;
	}

	public TAccountUser setEmail(String email) {
		this.email = email;
		return this;
	}

	public TAccountUser setName(String name) {
		this.name = name;
		return this;
	}

	public TAccountUser setPassword(String password) {
		this.password = password;
		return this;
	}

	public TAccountUser setRegisterHash(String registerHash) {
		this.registerHash = registerHash;
		return this;
	}

	public TAccountUser setStatus(int i) {
		this.status = i;
		return this;
	}

	public TAccountUser setUsername(String username) {
		this.username = username;
		return this;
	}

	public TAccountUser setUtcCreated(Date utcCreated) {
		this.utcCreated = utcCreated;
		return this;
	}

	public TAccountUser setUtcModified(Date utcModified) {
		this.utcModified = utcModified;
		return this;
	}

	public TAccountUser setTAccount(TAccount tAccount) {
		this.tAccount = tAccount;
		return this;
	}

	public Integer getId() {
		return id;
	}

	public String getEmail() {
		return this.email;
	}

	public String getName() {
		return this.name;
	}

	public String getPassword() {
		return this.password;
	}

	public String getRegisterHash() {
		return this.registerHash;
	}

	public int getStatus() {
		return this.status;
	}

	public String getUsername() {
		return this.username;
	}

	public Date getUtcCreated() {
		return this.utcCreated;
	}

	public Date getUtcModified() {
		return this.utcModified;
	}
	
	@JsonIgnore
	public TAccount getTAccount() {
		return this.tAccount;
	}

}