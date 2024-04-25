package com.esun.socialMedia.service;

import java.util.List;
import java.util.UUID;

import com.esun.socialMedia.model.Comment;

public interface CommentService {
	List<Comment> getCommentByPostID(Long post_id);
	String saveComment(Comment comment, UUID user_id, Long post_id);
	String updateComment(Comment comment, UUID user_id, Long post_id);
	String removeComment(Long comment_id, UUID user_id);
}
