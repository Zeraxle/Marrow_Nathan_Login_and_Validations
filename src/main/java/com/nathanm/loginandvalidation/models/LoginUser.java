package com.nathanm.loginandvalidation.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class LoginUser {
	
	@NotEmpty(message = "Username must not be empty")
	@Email(message = "Email must be valid")
	private String email;
	
	@NotEmpty(message = "Password must not be empty")
	@Size(min = 8, max = 255, message = "Password must be at least 8 characters")
	private String password;
	
	public LoginUser(){}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
