package com.learn.account.model.dto;

import javax.validation.constraints.NotNull;

public class LoginDTO {
	
	@NotNull
	private String email;
	
	@NotNull
	private String password;

	public LoginDTO setEmail(String email) {
		this.email = email;
		return this;
	}

	public LoginDTO setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
	
	

}
