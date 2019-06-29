package com.learn.account.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.learn.account.exception.AccountUserException;
import com.learn.account.model.dto.*;
import com.learn.account.model.po.TAccount;
import com.learn.account.model.po.TAccountCompany;
import com.learn.account.model.po.TAccountUser;
import com.learn.account.repository.TAccountCompanyRepository;
import com.learn.account.repository.TAccountRepository;
import com.learn.account.repository.TAccountUserRepository;
import com.learn.account.util.AccountDataConvert;
import com.learn.account.util.AccountPasswordSecurity;
import com.learn.account.util.CompanyDataConvert;
import com.learn.account.util.UserDataConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AccountService {
	
	@Autowired
	private TAccountRepository accountRepo;
	@Autowired
	private TAccountCompanyRepository companyRepo;
	@Autowired
	private TAccountUserRepository userRepo;
	
	@Autowired	
	private AccountDataConvert accountConvert;
	@Autowired
	private CompanyDataConvert companyConvert;
	@Autowired
	private UserDataConvert userConvert;
	
	@Autowired
	private AccountPasswordSecurity passwordSecurity;
	
	/*
	 * Account API
	 */
	
	@Transactional(rollbackFor = AccountUserException.class)
	public AccountDTO saveAccount(AccountDTO accountDTO)  {
		
		System.out.println("service works");
		Date cur = new Date();
		TAccount tAccount = accountConvert.generateTAccount(accountDTO);
		tAccount.setUtcCreated(cur).setUtcModified(cur);
		
		//save account PO and add returns into an account DTO for ResponseEntity
		TAccount respTAccount = accountRepo.save(tAccount);
		
		AccountDTO respAccountDto = accountConvert.generateAccountDTO(respTAccount);
		
		
		TAccountCompany tAccountCompany;
		List<TAccountUser> tAccountUserList;
		
		if(accountDTO.getAccountCompanyDTO() != null) {
			System.out.println("has company information");
			
			if(companyRepo.findAllABN().contains(accountDTO.getAccountCompanyDTO().getAbn())) {
				throw new AccountUserException("the company :" + accountDTO.getAccountCompanyDTO().getAbn() + "is already existed");
			}
			
			tAccountCompany = companyConvert.generateTAccountCompany(accountDTO.getAccountCompanyDTO(), tAccount);
			tAccountCompany.setUtcCreated(cur).setUtcModified(cur);
			
			//save company PO and add returns into a company DTO for ResponseEntity
			TAccountCompany respCompany = companyRepo.save(tAccountCompany);
			respAccountDto.setAccountCompanyDTO(companyConvert.generateAccountCompanyDTO(respCompany));
		}
		
		
		if(accountDTO.getAccountUsersDtoList() == null) {
			
			throw new AccountUserException("requires at least one user");
			
		} else {
			List<TAccountUser> respUserList = new ArrayList<>();
			
			tAccountUserList = userConvert.getTAccountUserList(accountDTO.getAccountUsersDtoList(), tAccount);
			
			for(TAccountUser tAccountUser : tAccountUserList) {

				if(userRepo.findByEmail(tAccountUser.getEmail()) != null) {
					throw new AccountUserException("the email address :"+ "\""+ tAccountUser.getEmail() +"\" is already existed");
				}
				
				tAccountUser.setUtcCreated(cur).setUtcModified(cur);
				//save user PO and add returns into a user list DTO for ResponseEntity
				respUserList.add(userRepo.save(tAccountUser));
			}
			respAccountDto.setAccountUsersDtoList(userConvert.getAccountUserDtoList(respUserList));
		}
		
		return respAccountDto;
	}
	
	
	public AccountDTO findOneAccount(int accountID) {
		TAccount tAccount = accountRepo.findById(accountID);
		TAccountCompany tAccountCompany = companyRepo.findByAccountId(tAccount.getId());

		List<TAccountUser> tAccountUserList = userRepo.findByAccountId(accountID);
		
		AccountDTO respAccountDTO = accountConvert.generateAccountDTO(tAccount)
				.setAccountCompanyDTO(companyConvert.generateAccountCompanyDTO(tAccountCompany))
				.setAccountUsersDtoList(userConvert.getAccountUserDtoList(tAccountUserList));
		
		return respAccountDTO;
	}
	
	@Transactional(rollbackFor = AccountUserException.class)
	public AccountDTO updateAccount(AccountDTO accountDTO) {
		
		TAccount tAccount = accountConvert.generateTAccount(accountDTO);
		
		if(accountDTO.getId() <= 0) {
			throw new AccountUserException("require a correct account id");
		} else {
			TAccount originalAccount = accountRepo.findById(accountDTO.getId());
			tAccount.setUtcCreated(originalAccount.getUtcCreated());
		}
		
		tAccount.setUtcModified(new Date());
		AccountDTO respAccountDTO = accountConvert.generateAccountDTO(accountRepo.save(tAccount));
		
		return respAccountDTO;
	}
	
	/*
	 * User API
	 */
	
	public PageDTO<AccountUserDTO> getUsers(int accountID, int page, int size) {
		
		Pageable pageWithTwoElements = PageRequest.of(page, size);
		
		Page<TAccountUser> tAccountUserList = userRepo.findByAccountId(accountID, pageWithTwoElements);
		
//		TAccount tAccount = accountRepo.findById(accountID);
		
//		Page<TAccountUser> tAccountUserList = userRepo.findByTAccount(tAccount, pageWithTwoElements);
		
		PageDTO<AccountUserDTO> accountUserDtoList = new PageDTO<>();
		
		List<AccountUserDTO> contentDtoList = tAccountUserList.getContent()
				.stream()
				.map(tAccountUser -> {
					return userConvert.generateAccountUserDTO(tAccountUser);
				})
				.collect(Collectors.toList());
		
		accountUserDtoList.setContent(contentDtoList)
				.setPageIndex(tAccountUserList.getNumber())
				.setPageSize(tAccountUserList.getSize())
				.setTotalPages(tAccountUserList.getTotalPages())
				.setTotalElements(tAccountUserList.getTotalElements());

		return accountUserDtoList;
	}
	
	public AccountDTO userLogin(LoginDTO loginDTO) {
		
		TAccountUser tAccountUser = userRepo.findByEmail(loginDTO.getEmail());
		
		if(tAccountUser == null) {
			throw new AccountUserException("no such user");
		}
		
		if(!passwordSecurity.getEncryptedPassword(loginDTO.getPassword()).equals(tAccountUser.getPassword())) {
			throw new AccountUserException("password is not correct, please retry");
		}
		
		TAccount tAccount = accountRepo.findById(tAccountUser.getTAccount().getId());
		TAccountCompany tAccountCompany = companyRepo.findById(tAccount.getId());
		
		
		List<TAccountUser> userList = new ArrayList<>();
		userList.add(tAccountUser);//only see one user information
		
		AccountDTO respAccountDTO = accountConvert.generateAccountDTO(tAccount)
				.setAccountCompanyDTO(companyConvert.generateAccountCompanyDTO(tAccountCompany))
				.setAccountUsersDtoList(userConvert.getAccountUserDtoList(userList)); 
		
		return respAccountDTO;
		
		
	}
	
	@Transactional(rollbackFor = AccountUserException.class)
	public List<AccountUserDTO> addUsers(List<AccountUserDTO> accountUserDtoList, int accountID) {
		
		Date cur = new Date();
		TAccount tAccount = accountRepo.findById(accountID);
		
		if(tAccount == null) {
			throw new AccountUserException("no such account");
		}
		
		List<TAccountUser> tAccountUserList = userConvert.getTAccountUserList(accountUserDtoList, tAccount);
		
		for(TAccountUser tAccountUser : tAccountUserList) {
			
			if(userRepo.findByEmail(tAccountUser.getEmail()) != null) {
				throw new AccountUserException("the email address :"+ "\""+ tAccountUser.getEmail() +"\" is already existed");
			}
			
			tAccountUser.setUtcCreated(cur).setUtcModified(cur);
			
			userRepo.save( tAccountUser);
		}
		
		List<AccountUserDTO> respAccountUserList = userConvert.getAccountUserDtoList(userRepo.findByAccountId(accountID));
		
		return respAccountUserList;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public AccountUserDTO updateUser(AccountUserDTO accountUserDTO, int id) {
		
		TAccountUser oldUser = userRepo.findById(id);
		//TODO
		
		TAccountUser tAccountUser = userConvert.generateTAccountUser(accountUserDTO);
		
		tAccountUser.setUtcCreated(oldUser.getUtcCreated())
		.setUtcModified(new Date())
		.setTAccount(oldUser.getTAccount());
		
		TAccountUser respUser = userRepo.save(tAccountUser);
		

		
		return userConvert.generateAccountUserDTO(tAccountUser);
	}
	
	
	@Transactional(rollbackFor = Exception.class)
	public String deleteUser(int userID) {
		
		TAccountUser tAccountUser = userRepo.findById(userID);
		
		List<TAccountUser> userList = userRepo.findByAccountId(tAccountUser.getTAccount().getId());
		
		if(userList.size() == 1) {
			throw new AccountUserException("cannot delete the last user");
		}
		
		userRepo.deleteById(userID);
		
		return userID + "has been deleted";
	}
	
	
	
	/*
	 * Company API
	 */
	
	@Transactional(rollbackFor = Exception.class)
	public AccountCompanyDTO updateCompany(AccountCompanyDTO accountCompanyDTO, int accountID) {
		
		TAccount tAccount = accountRepo.findById(accountID);
		
		TAccountCompany oldCompany;
		
		if(accountCompanyDTO.getId() <= 0) {
			throw new AccountUserException("updating company information must require its ID");
		} else {
			oldCompany = companyRepo.findById(accountCompanyDTO.getId());
		}
		
		TAccountCompany tAccountCompany = companyConvert.generateTAccountCompany(accountCompanyDTO, tAccount)
				.setUtcCreated(oldCompany.getUtcCreated())
				.setUtcModified(new Date());
		
		TAccountCompany respTAccountCompany = companyRepo.save(tAccountCompany);
		
		
		return companyConvert.generateAccountCompanyDTO(respTAccountCompany);
		
	}
	
	

}
