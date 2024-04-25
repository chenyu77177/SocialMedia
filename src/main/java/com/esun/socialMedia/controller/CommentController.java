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

import com.esun.socialMedia.model.Comment;
import com.esun.socialMedia.service.CommentService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	@GetMapping("/comment")
	public List<Comment> getCommentByPostId(@RequestParam(value = "post_id") Long post_id){
		return commentService.getCommentByPostID(post_id);
	}
	
	@PostMapping("/comment/newComment")
	public String saveComment(@RequestBody Comment comment, @RequestParam(value = "user_id") UUID user_id, @RequestParam(value = "post_id") Long post_id) {
		return commentService.saveComment(comment, user_id, post_id);
	}
	
	@PostMapping("/comment/update/{comment_id}")
	public String updateComment(@RequestBody Comment comment,@PathVariable Long comment_id , @RequestParam(value = "user_id") UUID user_id, @RequestParam(value = "post_id") Long post_id) {
		comment.setComment_id(comment_id);
		return commentService.updateComment(comment, user_id, post_id);
	}
	
	@DeleteMapping("/comment/remove/{comment_id}")
	public String removeComment(@PathVariable Long comment_id, @RequestParam(value = "user_id") UUID user_id) {
		return commentService.removeComment(comment_id, user_id);
	}
	
}
