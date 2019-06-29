package com.learn.account.model.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AccountDTO {
	
	@Digits(integer=10, fraction = 0)
	private int id;
	
	@NotNull
	@Digits(integer=3, fraction = 0)
	private int type;
	
	@NotNull
	@Size(min=2, max=20)
	private String languageCode;
	
	@NotNull
	@Size(min=2, max=100)
	private String timezone;
	
	@NotNull
	@Digits(integer=9, fraction=2)
	private BigDecimal credit;
	
	@NotNull
	@Digits(integer=9, fraction=2)
	private BigDecimal balance;
	
	@NotNull
	@Digits(integer=3, fraction = 0)
	private int status;
	
	
	private AccountCompanyDTO accountCompanyDTO;
	
	private List<AccountUserDTO> accountUsersDtoList;

	public AccountDTO setId(int id) {
		this.id = id;
		return this;
	}

	public AccountDTO setType(int type) {
		this.type = type;
		return this;
	}

	public AccountDTO setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
		return this;
	}

	public AccountDTO setTimezone(String timezone) {
		this.timezone = timezone;
		return this;
	}

	public AccountDTO setCredit(BigDecimal credit) {
		this.credit = credit;
		return this;
	}

	public AccountDTO setBalance(BigDecimal balance) {
		this.balance = balance;
		return this;
	}

	public AccountDTO setStatus(int status) {
		this.status = status;
		return this;
	}

	public AccountDTO setAccountCompanyDTO(AccountCompanyDTO tAccountCompany) {
		this.accountCompanyDTO = tAccountCompany;
		return this;
	}

	public AccountDTO setAccountUsersDtoList(List<AccountUserDTO> tAccountUsers) {
		this.accountUsersDtoList = tAccountUsers;
		return this;
	}

	public int getId() {
		return id;
	}

	public int getType() {
		return type;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public String getTimezone() {
		return timezone;
	}

	public BigDecimal getCredit() {
		return credit;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public int getStatus() {
		return status;
	}

	public AccountCompanyDTO getAccountCompanyDTO() {
		return accountCompanyDTO;
	}

	public List<AccountUserDTO> getAccountUsersDtoList() {
		return accountUsersDtoList;
	}
	
	
	
	
	

}
