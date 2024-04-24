package com.esun.socialMedia.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esun.socialMedia.model.Post;
import com.esun.socialMedia.model.User;
import com.esun.socialMedia.repository.PostRepository;
import com.esun.socialMedia.repository.UserRepositoryByUUID;

@Service
public class PostRepositoryJPAImple implements PostService {
	
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private UserRepositoryByUUID userRepositoryByUUID;
	
	@Override
	public List<Post> getAllPost() {
		List<Post> postList = new ArrayList<>();
		postRepository.findAll().forEach(post -> postList.add(post));
		return postList;
	}

	@Override
	public String savePost(Post post, UUID user_id) {
		String msg = "";
		
		Optional<User> userOptional = userRepositoryByUUID.findById(user_id);
		if(userOptional.isPresent() != false) {
			User userDb = userOptional.get();
			post.setUser(userDb);
			postRepository.save(post);
			msg = "success";
		}else {
			msg = "user_id 錯誤";
		}
		return msg;
	}

	@Override
	public String updateUser(Post post, UUID user_id) {
		String msg = "";
		Optional<Post> postOptional = postRepository.findById(post.getPost_id());
		UUID postFromUserID = postOptional.get().getUser().getUser_id();
		if(!postFromUserID.equals(user_id)) {
			msg = "無此權限";
			return msg;
		}
		if(postOptional.isPresent() != false) {
			Post postDb = postOptional.get();
			if(post.getContent() != null) {
				postDb.setContent(post.getContent());
				postRepository.save(postDb);
				msg = "success";
			}
		}else {
			msg = "無資料須更新";
		}
		return msg;
	}

	@Override
	public boolean removeUser(Long post_id) {
		boolean state = false;
		Optional<Post> postOptional = postRepository.findById(post_id);
		if(postOptional.isPresent() != false) {
			postRepository.deleteById(post_id);
			state = true;
		}
		return state;
	}

}
