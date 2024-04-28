package com.esun.socialMedia.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import org.springframework.stereotype.Service;

@Service
public class PasswordEncoderService {
	//生成隨機鹽
	public String generateSalt() {
		byte[] salt = new byte[16];
		SecureRandom random =  new SecureRandom();
		random.nextBytes(salt);
		return Base64.getEncoder().encodeToString(salt);
	}
	
	//將密碼加鹽並雜湊
	public String hashPassword(String password, String salt) throws NoSuchAlgorithmException{
		String saltedPassword = salt + password;
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(saltedPassword.getBytes());
		return Base64.getEncoder().encodeToString(hash);
	}
	
	//驗證密碼
	public boolean verifyPassword(String providedPassword, String storedPassword, String salt) throws NoSuchAlgorithmException{
		String hashedPassword = hashPassword(providedPassword, salt);
		return hashedPassword.equals(storedPassword);
	}
}
