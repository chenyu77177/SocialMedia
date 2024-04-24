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
		User db = userRepository.save(user);
		return db.getUser_id().toString();
	}

	@Override
	
	public String updateUser(User user, UUID uuid) {
		String msg = "";
		Optional<User> userOptional = userRepositoryByUUID.findById(uuid);
		if(userOptional.isPresent() != false) {
			User userDb = userOptional.get();
			if (user.getUsername() != null){
				userDb.setUsername(user.getUsername());
			}
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
		if(userOptional.isPresent() != false) {
			userRepositoryByUUID.deleteById(uuid);
			state = true;
		}
		return state;
	}

	
}
