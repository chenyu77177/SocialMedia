package com.esun.socialMedia.controller;

import java.util.List;
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
//	@Autowired
//	private AuthenticationService authenticationService;
	
	 @GetMapping("/users")
	 public List<User> getAllUser(){
		 return userService.getAllUser();
	 }
	 
	 @PostMapping("/user/newUser")
	 public String saveUser(@RequestBody User user) {
		 return userService.saveUser(user);
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
