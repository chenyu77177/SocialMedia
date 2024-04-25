package com.esun.socialMedia.repository;

import org.springframework.data.repository.CrudRepository;

import com.esun.socialMedia.model.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {

}
