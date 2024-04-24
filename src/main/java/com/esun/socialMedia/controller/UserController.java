package com.esun.socialMedia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
	 
}
