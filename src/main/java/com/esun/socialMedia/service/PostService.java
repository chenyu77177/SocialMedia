package com.esun.socialMedia.service;

import java.util.List;
import java.util.UUID;

import com.esun.socialMedia.model.Post;

public interface PostService {
	List<Post> getAllPost();
	Post getPostById(Long post_id);
	String savePost(Post post, UUID user_id);
	String updateUser(Post post, UUID user_id);
	String removeUser(Long post_id, UUID user_id);
}
