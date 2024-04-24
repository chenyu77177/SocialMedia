package com.esun.socialMedia.repository;

import org.springframework.data.repository.CrudRepository;

import com.esun.socialMedia.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
