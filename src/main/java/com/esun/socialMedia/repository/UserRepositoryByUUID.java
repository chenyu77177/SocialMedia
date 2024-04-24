package com.esun.socialMedia.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.esun.socialMedia.model.User;

public interface UserRepositoryByUUID extends CrudRepository<User, UUID> {

}
