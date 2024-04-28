package com.esun.socialMedia.service;

import java.util.List;
import java.util.UUID;

import com.esun.socialMedia.model.User;

public interface UserService {
	List<User> getAllUser();
	String saveUser(User user);
	String updateUser(User user, UUID uuid);
	boolean removeUser(UUID uuid);
	String login(String phone, String password);
}
