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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.esun.socialMedia.model.Post;
import com.esun.socialMedia.service.PostService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class PostController {
	@Autowired
	private PostService postService;
	
	@GetMapping("/posts")
	public List<Post> getAllPost(){
		return postService.getAllPost();
	}
	
	@GetMapping("/post/get")
	public Post getPost (@RequestParam(value = "post_id") Long post_id) {
		if(post_id != null && post_id != 0) {
			return postService.getPostById(post_id);			
		}
		return null;
	}
	
	@PostMapping("/post/newPost")
	public String savePost(@RequestBody Post post, @RequestParam(value = "user_id") UUID user_id) {
		return postService.savePost(post, user_id);
	}
	
	@PostMapping("/post/update/{post_id}")
	public String updatePost(@RequestBody Post post, @PathVariable Long post_id, @RequestParam(value = "user_id") UUID user_id) {
		post.setPost_id(post_id);
		return postService.updateUser(post, user_id);
	}
	
	@DeleteMapping("/post/remove/{post_id}")
	public String removePost(@PathVariable Long post_id, @RequestParam(value = "user_id") UUID user_id) {
		return postService.removeUser(post_id, user_id);
	}
}
