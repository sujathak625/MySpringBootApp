package com.sujatha.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sujatha.example.dao.MyRepository;
import com.sujatha.example.model.UserRegister;

@Service
public class MyAppService {

	@Autowired
	private MyRepository myRepository;

	public boolean registerUser(UserRegister userRegister) {
		myRepository.save(userRegister);
		UserRegister ur = myRepository.findByFirstName(userRegister.getFirstName());
		String fn = null;
		fn = ur.getFirstName();
		if (fn == null || "".equals(fn) || !fn.equals(userRegister.getFirstName())) {
			return false;
		} else if (fn.equals(userRegister.getFirstName())) {
			return true;
		}
		return false;
	}

}
