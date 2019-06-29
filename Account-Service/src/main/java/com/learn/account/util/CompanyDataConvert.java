package com.learn.account.util;

import com.learn.account.model.dto.AccountCompanyDTO;
import com.learn.account.model.po.TAccount;
import com.learn.account.model.po.TAccountCompany;
import org.springframework.stereotype.Component;



@Component
public class CompanyDataConvert {

	public AccountCompanyDTO generateAccountCompanyDTO(TAccountCompany tAccountCompany) {
		
		if(tAccountCompany == null) {
			return null;
		}
		
		AccountCompanyDTO accountCompanyDTO = new AccountCompanyDTO();
		accountCompanyDTO.setId(tAccountCompany.getId())
						.setAbn(tAccountCompany.getAbn())
						.setEmail(tAccountCompany.getEmail())
						.setPhone(tAccountCompany.getPhone())
						.setCompanyAddress1(tAccountCompany.getCompanyAddress1())
						.setCompanyAddress2(tAccountCompany.getCompanyAddress2())
						.setCompanyCountry(tAccountCompany.getCompanyCountry())
						.setCompanyName(tAccountCompany.getCompanyName())
						.setCompanyPostcode(tAccountCompany.getCompanyPostcode())
						.setCompanyState(tAccountCompany.getCompanyState());
		
		return accountCompanyDTO;
	}
	
	public TAccountCompany generateTAccountCompany(AccountCompanyDTO accountCompanyDTO, TAccount tAccount) {
		TAccountCompany tAccountCompany = new TAccountCompany();
		
		tAccountCompany.setId(accountCompanyDTO.getId())
						.setAbn(accountCompanyDTO.getAbn())
						.setPhone(accountCompanyDTO.getPhone())
						.setEmail(accountCompanyDTO.getEmail())
						.setCompanyAddress1(accountCompanyDTO.getCompanyAddress1())
						.setCompanyAddress2(accountCompanyDTO.getCompanyAddress2())
						.setCompanyCountry(accountCompanyDTO.getCompanyCountry())
						.setCompanyName(accountCompanyDTO.getCompanyName())
						.setCompanyPostcode(accountCompanyDTO.getCompanyPostcode())
						.setCompanyState(accountCompanyDTO.getCompanyState())
						.setCompanySuburb(accountCompanyDTO.getCompanySuburb())
						.setTAccount(tAccount);
		
		return tAccountCompany;
		
	}
}
