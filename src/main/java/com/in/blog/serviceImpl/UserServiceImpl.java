package com.in.blog.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.in.blog.DTO.UserDto;
import com.in.blog.Exception.ResourceNotFoundException;
import com.in.blog.config.AppConstants;
import com.in.blog.entity.Role;
import com.in.blog.entity.User;
import com.in.blog.repository.RoleRepo;
import com.in.blog.repository.UserRepo;
import com.in.blog.service.UserService;


@Service
//@Transactional
public class UserServiceImpl implements UserService 
{

	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;
	
	//this is done by the model mapper class.
	@Override
	public UserDto createUser(UserDto userDto) 
	{
		
		//DtoToUser
		User user = this.modelMapper.map(userDto, User.class);
		
		User savedUser = this.userRepo.save(user);
		
		//UserToDto
		return this.modelMapper.map(savedUser, UserDto.class);
	}
	
//	Converting from userDto to User entity MANUALLY
	
	/*
	 * public User dtoToUser(UserDto userDto) { 
	 * User user =
	 * this.modelMapper.map(userDto, User.class);
	 * 
	 * // User user = new User(); // user.setId(userDto.getId()); //
	 * user.setName(userDto.getName()); // user.setEmail(userDto.getEmail()); //
	 * user.setAbout(userDto.getAbout()); //
	 * user.setPassword(userDto.getPassword());
	 * 
	 * 
	 * return user; }
	 */
	
//	Converting from user entity to userDto MANUALLY
	
	/*
	 * public UserDto userToDto(User user) { UserDto userdto =
	 * this.modelMapper.map(user, UserDto.class);
	 * 
	 * UserDto userdto = new UserDto(); userdto.setName(user.getName());
	 * userdto.setEmail(user.getEmail()); userdto.setId(user.getId());
	 * userdto.setAbout(user.getAbout()); userdto.setPassword(user.getPassword());
	 * 
	 * return userdto; }
	 */

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) 
	{
		
		//DtoToUser
		User user = this.userRepo.findById(userId)
			.orElseThrow(()-> new ResourceNotFoundException("User", "UserId", userId));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setId(userDto.getId());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User UpdatedUser = this.userRepo.save(user);
		
		//UserToDto
		UserDto userDto1 = this.modelMapper.map(UpdatedUser, UserDto.class);
		
		return userDto1;
	} 

	
	@Override
	public UserDto getUserById(Integer userId) 
	{
		
		//DtoToUser
		User user = this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User", "UserId", userId));
		
		
		//UserToDto
		UserDto Dto = this.modelMapper.map(user, UserDto.class);
		return Dto;
	}

	@Override
	public List<UserDto> getAllUsers() 
	{
		
		//DtoToUser
		List<User> user2 = this.userRepo.findAll();
		
		//UserToDto
		List<UserDto> userdtos = 
				user2.stream().map(user->this.modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
		
		return userdtos;
	}

	@Override
	public void deleteUser(Integer userId) 
	{
		User user = this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User", "UserId", userId));
		
		this.userRepo.delete(user);
	}

	@Override
	public UserDto registerNewUser(UserDto userDto) {
		
		//UserToUserDto
		User user = this.modelMapper.map(userDto, User.class);
		
		//encoded the password
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		
		//fetching the role of user (NORMAL_USER)
		//Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();
		
		//fetching the role of user (ADMIN_USER)
		Role role1 = this.roleRepo.findById(AppConstants.ADMIN_USER).get();
		
		//setting the role to the user (NORMAL_USER)
		//user.getRoles().add(role);
		
		//setting the role to the user (ADMIN_USER)
		user.getRoles().add(role1);
		
		User newUser = this.userRepo.save(user);
		
		return this.modelMapper.map(newUser, UserDto.class);
	}


}
