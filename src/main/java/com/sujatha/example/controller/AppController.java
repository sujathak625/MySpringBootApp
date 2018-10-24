package com.sujatha.example.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sujatha.example.Utilities.Utilities;
import com.sujatha.example.model.Feedback;
import com.sujatha.example.model.User;
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
		StringBuilder loggedIn = new StringBuilder("false");
		mv.addObject("loggedInStatus", loggedIn.toString());
		if (countriesList != null) {
			mv.addObject("countries", countriesList);
		}
		mv.setViewName("header");
		return mv;
	}

	@RequestMapping("/logout")
	public ModelAndView logOut() {
		ModelAndView mv = new ModelAndView();
		StringBuilder loggedIn = new StringBuilder("false");
		mv.addObject("loggedInStatus", loggedIn.toString());
		mv.setViewName("header");
		return mv;
	}

	@RequestMapping(value = "/logIn", method = { RequestMethod.GET })
	public ModelAndView logIn(@Valid @ModelAttribute("logIn") User user) {
		ModelAndView mv = new ModelAndView();
		StringBuilder loggedIn = new StringBuilder("false");
		boolean isValidCredential = myService.isValidCredential(user);
		if (isValidCredential) {
			loggedIn = new StringBuilder("true");
			mv.addObject("user", myService.getUserFullName(user));
			mv.addObject("loggedInStatus", loggedIn.toString());
			mv.setViewName("UserPage");
		} else {
			mv.addObject("user", myService.getUserFullName(user));
			mv.addObject("loggedInStatus", loggedIn.toString());
			mv.setViewName("home");
		}
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

	@RequestMapping(value = "/preLoad")
	public ModelAndView getPreloadedData() {
		ModelAndView mv = new ModelAndView();
		List<String> countriesList = myService.getCountries();
		if (countriesList != null) {
			mv.addObject("countries", countriesList);
		}
		mv.setViewName("header");
		return mv;
	}

	@RequestMapping(value = "/userRegister", method = RequestMethod.POST)
	public ModelAndView registerNewUser(@Valid @ModelAttribute("userRegister") UserRegister userRegister,
			BindingResult result) {
		ModelAndView mv = new ModelAndView();
		String status = "status";
		if (result.hasErrors()) {
			mv.addObject(status, "error occured");
		}
		boolean updateStatus = false;
		updateStatus = myService.registerUser(userRegister);
		mv.addObject("updateStatus", updateStatus);
		if (updateStatus) {
			mv.addObject("status", "User added successfully");
		} else {
			mv.addObject("status", "User not added successfully");
		}
		mv.setViewName("header");
		return mv;
	}
	
	@RequestMapping(value="/feedback")
	public ModelAndView feedbackPage() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("status","None");
		mv = fbListInView(mv);
		mv.setViewName("feedback");
		return mv;
	}
	
	@RequestMapping(value="/deleteFeedback/{id}",method=RequestMethod.DELETE)
	public ModelAndView deletefeedback(@PathVariable("id") String id) {
		ModelAndView mv = new ModelAndView();
		boolean isDeleted = myService.deleteFeedback(id);
		mv = fbListInView(mv);
		mv.setViewName("feedback");
		return mv;
	}

	@RequestMapping(value="/addFeedback",method=RequestMethod.POST)
	public ModelAndView getFeedback(@Valid @ModelAttribute("addFeedback") Feedback feedback) {
		ModelAndView mv = new ModelAndView();
		Date currentTimeStamp = Utilities.getCurrentTimeStamp();
		feedback.setTimestamp(currentTimeStamp);
		boolean isSaveSuccess = false;
		isSaveSuccess = myService.saveFeedBack(feedback);
		if(isSaveSuccess) {
			mv.addObject("status","Success");
		}else {
			mv.addObject("status","Failed");
		}
		mv = fbListInView(mv);
		mv.setViewName("feedback");
		return mv;
	}

	
	private List<Feedback> getAllFeedbacks() {
		List<Feedback> fbList = new ArrayList<Feedback>();
		fbList = myService.getAllFeedbacks();
		return fbList;
	}
	
	private ModelAndView fbListInView(ModelAndView mv) {
		List<Feedback> fbList = new ArrayList<Feedback>();
		fbList = getAllFeedbacks();
		mv.addObject("feedbackList", fbList);
		return mv;
	}
	
	
	
}
