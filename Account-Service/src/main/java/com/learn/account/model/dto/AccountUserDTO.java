package com.learn.account.model.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AccountUserDTO {

	@Digits(integer=10, fraction = 0)
	private int id;
	
	@NotNull(message="must have an email")
	@Size(min=4, max=255, message="must have an email")
	@Email
	private String email;
	
	@NotNull
	@Size(min=1, max=45)
	private String name;
	
	@NotNull
	@Size(min=8, max=32)
	private String password;
	
	@NotNull
	@Size(min=0, max=255)
	private String registerHash;
	
	@NotNull
	@Digits(integer=3, fraction = 0)
	private int status;
	
	@Size(min=0, max=16)
	private String username;
	
//	@NotNull
//	private TAccountDTO TAccount;

	public AccountUserDTO setId(int id) {
		this.id = id;
		return this;
	}

	public AccountUserDTO setEmail(String email) {
		this.email = email;
		return this;
	}

	public AccountUserDTO setName(String name) {
		this.name = name;
		return this;
	}

	public AccountUserDTO setPassword(String password) {
		this.password = password;
		return this;
	}

	public AccountUserDTO setRegisterHash(String registerHash) {
		this.registerHash = registerHash;
		return this;
	}

	public AccountUserDTO setStatus(int status) {
		this.status = status;
		return this;
	}

	public AccountUserDTO setUsername(String username) {
		this.username = username;
		return this;
	}

//	public TAccountUserDTO setTAccount(TAccountDTO tAccount) {
//		TAccount = tAccount;
//		return this;
//	}

	public int getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getRegisterHash() {
		return registerHash;
	}

	public int getStatus() {
		return status;
	}

	public String getUsername() {
		return username;
	}

//	public TAccountDTO getTAccount() {
//		return TAccount;
//	}
	
	
}
