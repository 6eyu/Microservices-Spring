package com.learn.account.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import org.springframework.stereotype.Component;

@Component
public class AccountPasswordSecurity {
	
	public String getEncryptedPassword(String password) {
		
		MessageDigest md;
		String myHash = null;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] digest = md.digest();
			myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return myHash;
		
	}
	
	public boolean checkPassword(String input, String password) {
		return input.equals(password);
	}
	

}
