package com.in.blog.service;

import java.util.List;

import com.in.blog.DTO.UserDto;

public interface UserService {

//	method to addUser(create User)
	
	UserDto registerNewUser(UserDto user);
	
	UserDto createUser(UserDto user);
	
	UserDto updateUser(UserDto user, Integer userId);
	
	UserDto getUserById(Integer userId);
	
	List<UserDto> getAllUsers();
	
	void deleteUser(Integer userId);

	
}
