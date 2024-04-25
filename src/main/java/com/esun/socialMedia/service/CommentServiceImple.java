package com.esun.socialMedia.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esun.socialMedia.model.Comment;
import com.esun.socialMedia.model.Post;
import com.esun.socialMedia.model.User;
import com.esun.socialMedia.repository.CommentRepository;
import com.esun.socialMedia.repository.PostRepository;
import com.esun.socialMedia.repository.UserRepositoryByUUID;

@Service
public class CommentServiceImple implements CommentService {

	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private UserRepositoryByUUID userRepositoryByUUID;
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public List<Comment> getCommentByPostID(Long post_id) {
		List<Comment> commentList = new ArrayList<>();
		Optional<Post> commentOptional = postRepository.findById(post_id);
		//檢查貼文是否存在
		if(commentOptional.isPresent() != false) {
			commentList.addAll(postRepository.findById(post_id).get().getComments());			
			return commentList;
		}
		return null;
	}

	@Override
	public String saveComment(Comment comment, UUID user_id, Long post_id) {
		String msg = "";
		Optional<User> userOptional = userRepositoryByUUID.findById(user_id);
		Optional<Post> postOptional = postRepository.findById(post_id);
		if(comment.getContent() == null || comment.getContent() == "") {
			msg = "空值";
			return msg;
		}
		if(userOptional.isPresent() != false && postOptional.isPresent()!= false) {
			User userDb = userOptional.get();
			Post postDb = postOptional.get();
			comment.setUser(userDb);
			comment.setPost(postDb);
			commentRepository.save(comment);
			msg = "success";
		}else {
			msg = "user_id or post_id 錯誤";
		}
		
		return msg;
	}

	@Override
	public String updateComment(Comment comment, UUID user_id, Long post_id) {
		String msg = "";
		Optional<User> userOptional = userRepositoryByUUID.findById(user_id);
		Optional<Post> postOptional = postRepository.findById(post_id);
		Optional<Comment> commentOptional = commentRepository.findById(comment.getComment_id());
		Comment commentTemp;
		//取得留言者
		UUID commentFromUserId = commentOptional.get().getUser().getUser_id();
		//檢查留言是否存在
		if(commentOptional.isPresent() != false) {
			commentTemp = commentOptional.get();
		}else {
			msg = "comment_id 錯誤";
			return msg;
		}
		//檢查user及貼文是否存在
		if(userOptional.isPresent() != false && postOptional.isPresent()!= false) {
			User userDb = userOptional.get();
			Post postDb = postOptional.get();
			commentTemp.setUser(userDb);
			commentTemp.setPost(postDb);
			commentTemp.setContent(comment.getContent());
			commentRepository.save(commentTemp);
			msg = "success";
		}else {
			msg = "user_id or post_id 錯誤";
			return msg;
		}
		//比對留言人身分
		if(!commentFromUserId.equals(user_id)) {
			msg = "無此權限";
			return msg;
		}
		return msg;
	}

	@Override
	public String removeComment(Long comment_id, UUID user_id) {
		String msg = "";
		Optional<User> userOptional = userRepositoryByUUID.findById(user_id);
		Optional<Comment> commentOptional = commentRepository.findById(comment_id);
		//取得留言者
		UUID commentFromUserId = commentOptional.get().getUser().getUser_id();
		//比對留言人身分
		if(!commentFromUserId.equals(user_id)) {
			msg = "無操作權限";
			return msg;
		}
		//檢查留言及user是否存在
		if(commentOptional.isPresent() != false && userOptional.isPresent() != false) {
			commentRepository.deleteById(comment_id);
			msg = "success";
		}
		return msg;
	}

}
