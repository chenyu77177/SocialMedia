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
	public Post getPostById(Long post_id) {
		Post post = new Post();
		Optional<Post> postOptional = postRepository.findById(post_id);
		if(postOptional.isPresent()) {
			post = postOptional.get();
			return post;
		}
		return null;
	}

	@Override
	public String savePost(Post post, UUID user_id) {
		String msg = "";
		
		Optional<User> userOptional = userRepositoryByUUID.findById(user_id);
		//user是否存在
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
		//取得發文者ID
		UUID postFromUserID = postOptional.get().getUser().getUser_id();
		//身分驗證
		if(!postFromUserID.equals(user_id)) {
			msg = "無此權限";
			return msg;
		}
		//檢查貼文是否存在
		if(postOptional.isPresent() != false) {
			Post postDb = postOptional.get();
			//檢查是否變更內容
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
	public String removeUser(Long post_id, UUID user_id) {
		String msg = "";
		Optional<Post> postOptional = postRepository.findById(post_id);
		Optional<User> userOptional = userRepositoryByUUID.findById(user_id);
		//檢查user 是否存在
		if(userOptional == null) {
			msg = "user 不存在";
			return msg;
		}
		//非發文者
		if(!postOptional.get().getUser().getUser_id().equals(user_id)) {
			msg = "無操作權限";
			return msg;
		}
		//貼文存在
		if(postOptional.isPresent() != false) {
			postRepository.deleteById(post_id);
			msg = "success";
		}
		return msg;
	}

}
