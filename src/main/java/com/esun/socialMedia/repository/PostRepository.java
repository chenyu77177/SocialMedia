package com.esun.socialMedia.repository;

import org.springframework.data.repository.CrudRepository;

import com.esun.socialMedia.model.Post;

public interface PostRepository extends CrudRepository<Post, Long> {

}
