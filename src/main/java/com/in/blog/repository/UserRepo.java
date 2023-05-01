package com.in.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.in.blog.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{

	Optional<User> findByEmail(String email);
	
}
