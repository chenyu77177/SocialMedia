package com.esun.socialMedia.service;

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
//	@Autowired
//	private PWDEncoderService pwdEncoderService;
	
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
//		//密碼加鹽並hash
//		user.setPassword(pwdEncoderService.encodePassword(user.getPassword(), pwdEncoderService.generateSalt()));
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

	
}
