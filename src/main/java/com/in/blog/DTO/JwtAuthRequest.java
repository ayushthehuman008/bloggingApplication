package com.in.blog.DTO;

public class JwtAuthRequest {

	private String username;
	
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public JwtAuthRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public JwtAuthRequest() {
		super();
	}

	@Override
	public String toString() {
		return "JwtAuthRequest [username=" + username + ", password=" + password + "]";
	}
	
	
	
}
