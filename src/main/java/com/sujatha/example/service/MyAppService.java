package com.sujatha.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sujatha.example.dao.CountryRepository;
import com.sujatha.example.dao.MyRepository;
import com.sujatha.example.model.Country;
import com.sujatha.example.model.UserRegister;

@Service
public class MyAppService {

	@Autowired
	private MyRepository myRepository;
	
	@Autowired CountryRepository countryRepository;

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
	
	public List<String> getCountries() {
		List<String> countries = new ArrayList<String>();
		List<Country> countryList = new ArrayList<Country>();
		countryList = getCountriesData();
		for(Country country:countryList) {
			String name = country.getName();
			countries.add(name);
		}
		return countries;
	}
	
	private List<Country> getCountriesData() {		
		List<Country> countryList = new ArrayList<Country>();
		countryList = countryRepository.findAll();
		return countryList;
	}
}
