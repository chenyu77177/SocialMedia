package com.esun.socialMedia.service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esun.socialMedia.model.User;
import com.esun.socialMedia.repository.UserRepository;
import com.esun.socialMedia.repository.UserRepositoryByUUID;

@Service
public class UserServiceJPAImple implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserRepositoryByUUID userRepositoryByUUID;
	@Autowired 
	private PasswordEncoderService passwordEncoderService;
	
	@Override
	public List<User> getAllUser() {
		List<User> userList = new ArrayList<>();
		userRepository.findAll().forEach(user -> userList.add(user));
		return userList;
	}

	@Override
	public String saveUser(User user) {
		UUID uuid = UUID.randomUUID();
		user.setUser_id(uuid);
		//密碼加鹽並hash
		String salt = passwordEncoderService.generateSalt();
		user.setSalt(salt);
		try {
			String encryptedPassword = passwordEncoderService.hashPassword(user.getPassword(), salt);
			user.setPassword(encryptedPassword);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		User db = userRepository.save(user);
		return db.getUser_id().toString();
	}

	@Override
	
	public String updateUser(User user, UUID uuid) {
		String msg = "";
		Optional<User> userOptional = userRepositoryByUUID.findById(uuid);
		//檢查使用者是否存在
		if(userOptional.isPresent() != false) {
			User userDb = userOptional.get();
			//姓名是否變更
			if (user.getUsername() != null){
				userDb.setUsername(user.getUsername());
			}
			//介紹是否變更
			if(user.getBiography() != null) {
				userDb.setBiography(user.getBiography());				
			}
			userRepositoryByUUID.save(userDb);
			msg = "success";
		}else {
			//找不到UUID
			msg = "無此資料";
		}
		
		return msg;
	}

	@Override
	public boolean removeUser(UUID uuid) {
		boolean state = false;
		Optional<User> userOptional = userRepositoryByUUID.findById(uuid);
		//使用者是否存在
		if(userOptional.isPresent() != false) {
			userRepositoryByUUID.deleteById(uuid);
			state = true;
		}
		return state;
	}

	@Override
	public String login(String phone, String rawPassword) {
		Optional<User> userOptional = userRepository.findByPhone(phone);
		if(userOptional != null) {
			String salt = userOptional.get().getSalt();
			String storedPassword = userOptional.get().getPassword();
			boolean verify_result;
			try {
				verify_result = passwordEncoderService.verifyPassword(rawPassword, storedPassword, salt);
				if(verify_result) {
					return userOptional.get().getUser_id().toString();					
				}
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}
		return "failed";
	}

	
}
