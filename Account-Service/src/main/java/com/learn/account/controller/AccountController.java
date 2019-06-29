package com.learn.account.controller;


import java.util.List;

import javax.validation.Valid;

import com.learn.account.model.ResponseWrapper;
import com.learn.account.model.dto.*;
import com.learn.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Validated
@RestController
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	
	/*
	 * Account API
	 */
	
	@PostMapping("/account")
	public ResponseEntity createAccount(@RequestBody @Valid AccountDTO accountDTO)  {
		
		System.out.println("API works");
		
		AccountDTO response = accountService.saveAccount(accountDTO);
		
		ResponseWrapper<AccountDTO> wrapper = new ResponseWrapper<>(response, "success", HttpStatus.OK);

		return new ResponseEntity(wrapper, HttpStatus.OK);
	}
	
	@GetMapping("/account")
	public ResponseEntity getOneAccount(@RequestParam("id") int accountId) {
		
		AccountDTO response = accountService.findOneAccount(accountId);
		
		ResponseWrapper<AccountDTO> wrapper = new ResponseWrapper<>(response, "success", HttpStatus.OK);
		
		return new ResponseEntity(wrapper, HttpStatus.OK);
	}
	
	@PutMapping("/account")
	public ResponseEntity updateAccount(@RequestBody @Valid AccountDTO accountDTO)  {
		
		AccountDTO response = accountService.updateAccount(accountDTO);
		
		ResponseWrapper<AccountDTO> wrapper = new ResponseWrapper<>(response, "success", HttpStatus.OK);
		
		return new ResponseEntity(wrapper, HttpStatus.OK);
	}
	
	/*
	 * User API
	 */
	
	@GetMapping("/account/user/id")
	public ResponseEntity getUsers(@RequestParam("id") int accountId, @RequestParam("page") int page, @RequestParam("size") int size) {
		
		PageDTO<AccountUserDTO> response = accountService.getUsers(accountId, page, size);
		
		ResponseWrapper<PageDTO<AccountUserDTO>> wrapper = new ResponseWrapper<>(response, "success", HttpStatus.OK);
		
		return new ResponseEntity(wrapper, HttpStatus.OK);
		
	}
	
	@PostMapping("/account/user")
	public ResponseEntity createUsers(@Valid @RequestBody List<AccountUserDTO> accountUserDtoList, @RequestParam("id") int accountId) {
		
		System.out.println("test test");
		List<AccountUserDTO> response = accountService.addUsers(accountUserDtoList, accountId);
		ResponseWrapper<List<AccountUserDTO>> wrapper = new ResponseWrapper<>(response, "success", HttpStatus.OK);
		
		return new ResponseEntity(wrapper, HttpStatus.OK);
	}
	
	@PostMapping("/account/user/test")
	public void createUsers(@Valid @RequestBody List<AccountUserDTO> accountUserDtoList) {
		
	}
	
	@GetMapping("/account/user")
	public ResponseEntity userLogin(@RequestBody @Valid LoginDTO loginDTO) {
		AccountDTO response = accountService.userLogin(loginDTO);
		
		ResponseWrapper<AccountDTO> wrapper = new ResponseWrapper<>(response, "success", HttpStatus.OK);
		
		return new ResponseEntity(wrapper, HttpStatus.OK);
	}
	
	
	@PutMapping("/account/user")
	public ResponseEntity updateUser(@RequestBody @Valid AccountUserDTO accountUserDTO, @RequestParam("id")int userId) {
		
		AccountUserDTO response = accountService.updateUser(accountUserDTO, userId);
		
		ResponseWrapper<AccountUserDTO> wrapper = new ResponseWrapper<>(response, "success", HttpStatus.OK);
		
		return new ResponseEntity(wrapper, HttpStatus.OK);
	}
	
	@DeleteMapping("/account/user")
	public ResponseEntity deleteUser(@RequestParam("id") int userId) {
		
		String response = accountService.deleteUser(userId);
		
		ResponseWrapper<String> wrapper = new ResponseWrapper<>(response, "success", HttpStatus.OK);
		
		return new ResponseEntity(wrapper, HttpStatus.OK);
	}
	
	/*
	 * Company API
	 */
	
	@PutMapping("/account/company")
	public ResponseEntity updateCompany(@RequestBody @Valid AccountCompanyDTO accountCompanyDTO, @RequestParam("id") int accountId) {
		
		AccountCompanyDTO response = accountService.updateCompany(accountCompanyDTO, accountId);
		
		ResponseWrapper<AccountCompanyDTO> wrapper = new ResponseWrapper<>(response, "success", HttpStatus.OK);
		
		return new ResponseEntity(wrapper, HttpStatus.OK);
	}
	
	
}
