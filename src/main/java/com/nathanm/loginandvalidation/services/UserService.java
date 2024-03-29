package com.nathanm.loginandvalidation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nathanm.loginandvalidation.models.User;
import com.nathanm.loginandvalidation.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository repo;
	
	public User get(String email) {
		return repo.findByEmail(email);
	}
		
	public User get(Long id) {
		return repo.findById(id).orElse(null);
	}
	
	public User getByUsername(String username) {
		return repo.findByUsername(username);
	}
	
	public User create(User user) {
        return repo.save(user);
	}
	
	
	public User update(User user) {
		return repo.save(user);
	}
	
	public void destroy(Long id) {
		repo.deleteById(id);
	}
}
