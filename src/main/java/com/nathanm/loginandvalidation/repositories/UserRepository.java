package com.nathanm.loginandvalidation.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nathanm.loginandvalidation.models.User;

@Repository
public interface UserRepository extends CrudRepository <User, Long>{
	
	public User findByEmail(String email);
	
	public User findByUsername(String username);
	
	
}
