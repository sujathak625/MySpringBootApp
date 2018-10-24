package com.sujatha.example.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sujatha.example.model.Feedback;
import com.sujatha.example.model.User;
import com.sujatha.example.model.UserRegister;

public interface MyRepository extends MongoRepository<UserRegister, String> {

	public UserRegister findByFirstName(String firstName);

	public List<UserRegister> findByLastName(String lastName);
	
	public UserRegister findByUserName(String userName);

	
	
}
