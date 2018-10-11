package com.sujatha.example.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sujatha.example.model.Country;

public interface CountryRepository extends MongoRepository<Country, String> {	
	public List<Country> findByName();
}