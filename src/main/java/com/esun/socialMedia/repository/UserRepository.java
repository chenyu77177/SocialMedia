package com.esun.socialMedia.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.esun.socialMedia.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	Optional<User> findByPhone(String phone);
}
