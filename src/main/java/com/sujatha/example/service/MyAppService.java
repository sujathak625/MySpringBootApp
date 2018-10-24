package com.sujatha.example.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sujatha.example.Utilities.Utilities;
import com.sujatha.example.dao.CountryRepository;
import com.sujatha.example.dao.FeedbackRepository;
import com.sujatha.example.dao.MyRepository;
import com.sujatha.example.model.Country;
import com.sujatha.example.model.Feedback;
import com.sujatha.example.model.User;
import com.sujatha.example.model.UserRegister;

@Service
public class MyAppService {

	@Autowired
	private MyRepository myRepository;

	@Autowired
	CountryRepository countryRepository;

	@Autowired
	FeedbackRepository feedbackRepository;

	public boolean registerUser(UserRegister userRegister) {
		myRepository.save(userRegister);
		UserRegister ur = findUserByFirstName(userRegister);
		String fn = ur.getFirstName();
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
		for (Country country : countryList) {
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

	public boolean isValidCredential(User user) {
		boolean isValid = false;
		String userName = user.getUserName();
		String password = user.getPassword();
		UserRegister userDetails = findUserByUserName(userName);
		if (userDetails.getUserName().equalsIgnoreCase(userName) && userDetails.getPassword().equals(password)) {
			isValid = true;
		}
		return isValid;
	}

	public String getUserFullName(User user) {
		String userName = user.getUserName();
		UserRegister userDetails = findUserByUserName(userName);
		StringBuilder fn = new StringBuilder(userDetails.getFirstName());
		StringBuilder ln = new StringBuilder(userDetails.getLastName());
		String fullName = Utilities.concatinateStrings(fn.toString(), ln.toString());
		return fullName;
	}

	private UserRegister findUserByUserName(String userName) {
		UserRegister userDetails = myRepository.findByUserName(userName);
		return userDetails;
	}
	
	private Optional<Feedback> findFeedbackById(String id) {
		Optional<Feedback> fb= feedbackRepository.findById(id);
		return fb;
	}

	private UserRegister findUserByFirstName(UserRegister userRegister) {
		UserRegister ur = findUserByFirstName(userRegister.getFirstName());
		;
		return ur;
	}

	private UserRegister findUserByFirstName(String firstName) {
		UserRegister ur = myRepository.findByFirstName(firstName);
		return ur;
	}

	public boolean saveFeedBack(Feedback feedback) {
		boolean isSaveSuccess = false;
		feedbackRepository.save(feedback);
		Feedback fb = feedbackRepository.findByName(feedback.getName());
		if (fb != null && !"".equals(feedback.getName()) && feedback.getName().equals(fb.getName())) {
			isSaveSuccess = true;
		}
		return isSaveSuccess;
	}

	public List<Feedback> getAllFeedbacks() {
		List<Feedback> feedbackList = new ArrayList<Feedback>();
		feedbackList = feedbackRepository.findAll();

		Collections.sort(feedbackList, new Comparator<Feedback>() {
			public int compare(Feedback f1, Feedback f2) {
				return f2.getTimestamp().compareTo(f1.getTimestamp());
			}
		});
		return feedbackList;
	}
	
	public boolean deleteFeedback(String id) {
		boolean isDeleted = false;
		feedbackRepository.deleteFeedbackById(id);
		Optional<Feedback> fb = findFeedbackById(id);
		if(fb==null) {
			isDeleted = true;
		}
		return isDeleted;
	}
}
