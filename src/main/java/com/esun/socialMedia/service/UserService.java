package com.esun.socialMedia.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.esun.socialMedia.model.User;

public interface UserService {
	List<User> getAllUser();
	boolean userIdCheck(UUID uuid);
	String saveUser(User user);
	String updateUser(User user, UUID uuid);
	boolean removeUser(UUID uuid);
	Map<String, Object> login(String phone, String password);
}
