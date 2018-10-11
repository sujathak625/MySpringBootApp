package com.sujatha.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sujatha.example.model.UserRegister;
import com.sujatha.example.service.MyAppService;

@RestController
public class AppController {
	
	@Autowired
	MyAppService myService;
	
	@RequestMapping("/")
	public ModelAndView getHomePage() {
		ModelAndView mv = new ModelAndView();
		List<String> countriesList = myService.getCountries();
		if(countriesList!=null) {
			mv.addObject("countries",countriesList);
		}	
	    mv.setViewName("header");		
		return mv;	
	}
	
	@RequestMapping("/home")
	public ModelAndView goToHomePage() {
		ModelAndView mv = new ModelAndView();
	    mv.setViewName("home");		
		return mv;
	}
	
	@RequestMapping("/about")
	public ModelAndView getAboutPage() {
		ModelAndView mv = new ModelAndView();
	    mv.setViewName("about");	
		return mv;
	}
	
	@RequestMapping("/contact")
	public ModelAndView getContactPage() {
		ModelAndView mv = new ModelAndView();
	    mv.setViewName("contact");		
		return mv;
	}
	
	@RequestMapping(value="/preLoad")
	public ModelAndView getPreloadedData() {
		ModelAndView mv = new ModelAndView();
		List<String> countriesList = myService.getCountries();
		if(countriesList!=null) {
			mv.addObject("countries",countriesList);
		}	
	    mv.setViewName("header");		
		return mv;
	}

	@RequestMapping(value="/userRegister", method = RequestMethod.POST)
	public ModelAndView registerNewUser(@Valid @ModelAttribute("userRegister") UserRegister userRegister,BindingResult result) {
		ModelAndView mv = new ModelAndView();
		String status = "status";
		if (result.hasErrors()) {
			mv.addObject(status,"error occured");
	    }	
		boolean updateStatus = false;
		updateStatus = myService.registerUser(userRegister);
		mv.addObject("updateStatus",updateStatus);
		if(updateStatus) {			
			mv.addObject("status","User added successfully");
		} else {
			mv.addObject("status","User not added successfully");
		}
	    mv.setViewName("header");		
		return mv;
	}

	
}
