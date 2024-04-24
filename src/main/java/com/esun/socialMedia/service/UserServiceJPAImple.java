package com.esun.socialMedia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esun.socialMedia.model.User;
import com.esun.socialMedia.repository.UserRepository;

@Service
public class UserServiceJPAImple implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<User> getAllUser() {
		List<User> userList = new ArrayList<>();
		userRepository.findAll().forEach(user -> userList.add(user));
		return userList;
	}

}
