package com.learn.account.util;

import com.learn.account.model.dto.AccountDTO;
import com.learn.account.model.po.TAccount;
import org.springframework.stereotype.Component;




@Component
public class AccountDataConvert {
	
	
	public TAccount generateTAccount(AccountDTO accountDTO) {
		
		System.out.println("util Taccount works");
		TAccount tAccount = new TAccount();
		
		tAccount.setId(accountDTO.getId())
				.setBalance(accountDTO.getBalance())
				.setCredit(accountDTO.getCredit())
				.setTimezone(accountDTO.getTimezone())
				.setLanguageCode(accountDTO.getLanguageCode())
				.setStatus(accountDTO.getStatus())
				.setType(accountDTO.getType());
		
		
		
		return tAccount;
	}
	
	public AccountDTO generateAccountDTO(TAccount tAccount) {
		
		System.out.println("util DTO works");
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setId(tAccount.getId())
				.setBalance(tAccount.getBalance())
				.setCredit(tAccount.getCredit())
				.setLanguageCode(tAccount.getLanguageCode())
				.setStatus(tAccount.getStatus())
				.setTimezone(tAccount.getTimezone())
				.setType(tAccount.getType());
		
		return accountDTO;
	}
}
