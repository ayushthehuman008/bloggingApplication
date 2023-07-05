package com.in.blog.Api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in.blog.DTO.ApiResponse;
import com.in.blog.DTO.UserDto;
import com.in.blog.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserApi 
{
	@Autowired
	private UserService userService;
	
	//POST - create user
	
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto)
	{
		UserDto createUserdto = this.userService.createUser(userDto);
		return new ResponseEntity<UserDto>(createUserdto, HttpStatus.CREATED);
	}
	
	//PUT - update user
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser
	(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer userId)
	{
		UserDto updatedUser = this.userService.updateUser(userDto, userId);
		//return new ResponseEntity<UserDto>(updatedUser, HttpStatus.OK);
		return ResponseEntity.ok(updatedUser);
	}
	
	//GET - get user
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserById
	(@PathVariable Integer userId)
	{
		UserDto getUserById = this.userService.getUserById(userId);
		
		return new ResponseEntity<>(getUserById, HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers()
	{
		List<UserDto> getAllUser = this.userService.getAllUsers();
		
		return new ResponseEntity<>(getAllUser, HttpStatus.OK);
	}
	
	//ADMIN
	//DELETE - delete user
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId)
	{
		this.userService.deleteUser(userId);
		//return new ResponseEntity<>("User Deleted Successfully",HttpStatus.OK);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted Successfully", false), HttpStatus.OK);
	}

	
}
