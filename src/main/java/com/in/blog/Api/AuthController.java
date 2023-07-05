package com.in.blog.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in.blog.DTO.JwtAuthRequest;
import com.in.blog.DTO.JwtAuthResponse;
import com.in.blog.DTO.UserDto;
import com.in.blog.Exception.ApiException;
import com.in.blog.security.JwtTokenHelper;
import com.in.blog.service.UserService;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController 
{
	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	
	//register new user
		@PostMapping("/register")
		public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto)
		{
			UserDto registeredUser = this.userService.registerNewUser(userDto);
			return new ResponseEntity<UserDto>(registeredUser, HttpStatus.CREATED);
			
		}
		
		
	//create token
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken
	(@RequestBody JwtAuthRequest request) throws Exception
	{
		this.authenticate(request.getUsername(), request.getPassword());
		
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
		
		String token = this.jwtTokenHelper.generateToken(userDetails);
		
		JwtAuthResponse response = new JwtAuthResponse();
		response.setToken(token);
		return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
	}

	//by this method we can authenticate the user that he/she is providing the correct username and password or not.
	private void authenticate(Object username, Object password) throws Exception{
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

		try
		{
			this.authenticationManager.authenticate(authenticationToken);
		}
		catch(BadCredentialsException e)
		{
			System.out.println("Invalid Details!!");
			throw new ApiException("Invalid username or password");
		}
	}
	
}
