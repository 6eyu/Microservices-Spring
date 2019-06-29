package com.learn.account.util;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.learn.account.exception.AccountUserException;
import com.learn.account.model.dto.AccountUserDTO;
import com.learn.account.model.po.TAccount;
import com.learn.account.model.po.TAccountUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UserDataConvert {

	@Autowired
	private AccountPasswordSecurity passwordSecurity;
	
	public List<TAccountUser> getTAccountUserList(List<AccountUserDTO> accountUserDtoList, TAccount tAccount) {
		
		if(tAccount == null) {
			throw new AccountUserException("require account information when add users");
		}
		
		List<TAccountUser> tAccountUserList = new ArrayList<>();
		
		for(AccountUserDTO accountUserDTO : accountUserDtoList) {
			TAccountUser tAccountUser = generateTAccountUser(accountUserDTO)
					.setTAccount(tAccount);
			tAccountUserList.add(tAccountUser);
		}
		
		return tAccountUserList;
	}
	
	
	public List<AccountUserDTO> getAccountUserDtoList(List<TAccountUser> tAccountUserList) {
		List<AccountUserDTO> accountUserDtoList = new ArrayList<>();
		
		for(TAccountUser tAccountUser : tAccountUserList) {
			AccountUserDTO accountUserDTO = generateAccountUserDTO(tAccountUser);
			accountUserDtoList.add(accountUserDTO);
		}
		
		return accountUserDtoList;
	}
	
	public TAccountUser generateTAccountUser(@Valid AccountUserDTO accountUserDTO) {
		TAccountUser tAccountUser = new TAccountUser();
		
		String encryptedPassword = passwordSecurity.getEncryptedPassword(accountUserDTO.getPassword());
		
		if(encryptedPassword == null) {
			throw new AccountUserException("null encrypted password");
		}
		
		tAccountUser.setId(accountUserDTO.getId())
					.setName(accountUserDTO.getName())
					.setEmail(accountUserDTO.getEmail())
					.setPassword(encryptedPassword)
					.setRegisterHash(accountUserDTO.getRegisterHash()) //save encrypted password with MD5
					.setStatus(accountUserDTO.getStatus())
					.setUsername(accountUserDTO.getUsername());
		
		return tAccountUser;
	}
	
	public AccountUserDTO generateAccountUserDTO(TAccountUser tAccountUser) {
		AccountUserDTO accountUserDTO = new AccountUserDTO();
		
		accountUserDTO.setId(tAccountUser.getId())
					.setEmail(tAccountUser.getEmail())
					.setName(tAccountUser.getName())
					.setStatus(tAccountUser.getStatus())
					.setUsername(tAccountUser.getUsername());
		
		return accountUserDTO;
	}
	
}
