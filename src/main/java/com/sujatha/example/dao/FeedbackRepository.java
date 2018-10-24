package com.sujatha.example.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sujatha.example.model.Feedback;


public interface FeedbackRepository extends MongoRepository<Feedback, String> {
	
	public Feedback findByName(String name);
	
	public Long deleteFeedbackById(String id);

}
