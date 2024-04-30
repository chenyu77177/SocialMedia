package com.esun.socialMedia.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esun.socialMedia.model.User;
import com.esun.socialMedia.service.UserService;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserController {
	@Autowired
	private UserService userService;
	
	 @GetMapping("/users")
	 public List<User> getAllUser(){
		 return userService.getAllUser();
	 }
	 
	 @PostMapping("/user/login")
	 public Map<String, Object> login(@RequestBody Map<String, Object> userMap) {
		 return userService.login(userMap.get("phone").toString(), userMap.get("password").toString());
	 }
	 
	 @PostMapping("/user/newUser")
	 public String saveUser(@RequestBody Map<String, Object> user) {
		 User tempUser = new User();
		 tempUser.setUsername(user.get("username").toString());
		 tempUser.setPassword(user.get("password").toString());
		 tempUser.setPhone(user.get("phone").toString());
		 tempUser.setEmail(user.get("email").toString());
		 tempUser.setBiography(user.get("biography").toString());
		 return userService.saveUser(tempUser);
	 }
	 
	 @PostMapping("/user/update/{user_id}")
	 public String updateUser(@RequestBody User user, @PathVariable UUID user_id) {
		 return userService.updateUser(user, user_id);
	 }
	 
	 @DeleteMapping("/user/remove/{user_id}")
	 public boolean removeUser(@PathVariable UUID user_id) {
		 return userService.removeUser(user_id);
	 }
	 
//	 @PostMapping("/user/login")
//	 public boolean login(User user) {
//		 return authenticationService.authenticateUser(user.getPhone(), user.getPassword());
//	 }
	 
}
