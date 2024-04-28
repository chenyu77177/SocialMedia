package com.esun.socialMedia.runnable;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.esun.socialMedia.model.Comment;
import com.esun.socialMedia.model.Post;
import com.esun.socialMedia.model.User;
import com.esun.socialMedia.repository.CommentRepository;
import com.esun.socialMedia.repository.PostRepository;
import com.esun.socialMedia.repository.UserRepository;
import com.esun.socialMedia.service.PasswordEncoderService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SampleDataRunnable1 implements CommandLineRunner {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private PasswordEncoderService passwordEncoderService;
	private User user = new User();
	private Post post = new Post();
	private Comment comment = new Comment();


	@Override
	public void run(String... args) throws Exception {
		log.info("# add user");
		addUser();
		log.info("# add post");
		addPost();
		log.info("# add comment");
		addComment();
	}

	public void addUser() {
		user.setUsername("Andy");
		user.setPhone("0912345678");
		user.setEmail("andy123@gmail.com");
		user.setBiography("您好!我是安迪");
		String salt = passwordEncoderService.generateSalt();
		user.setSalt(salt);
		String encryptedPassword;
		try {
			encryptedPassword = passwordEncoderService.hashPassword("Abcd123456", salt);
			user.setPassword(encryptedPassword);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}		
		user = userRepository.save(user);
	}
	
	public void addPost() {
		post.setContent("今天天氣晴朗!");
		post.setUser(user);
		post = postRepository.save(post);
	}
	
	public void addComment() {
		comment.setContent("台北在下雨QAQ");
		comment.setUser(user);
		comment.setPost(post);
		comment = commentRepository.save(comment);
	}
}
