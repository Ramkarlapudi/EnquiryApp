package com.ramkarlapudi.EnquiryApp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ramkarlapudi.EnquiryApp.entity.CrendientialsEntity;
import com.ramkarlapudi.EnquiryApp.model.UserRegistration;
import com.ramkarlapudi.EnquiryApp.service.ProfileUpdateImp;

@Controller
public class UserController {

	@Autowired
	private ProfileUpdateImp profileUpdateImp;

	@RequestMapping(value = "/Profileupdate", method = RequestMethod.POST)
	public String updateUserProfile(@ModelAttribute("ProfileupdataData") UserRegistration userRegistration, Model model ,HttpServletRequest request) {
		System.out.println("Calling updateUserProfile " + userRegistration.getUsername());
		
		CrendientialsEntity ce = profileUpdateImp.updateUserData(userRegistration);
		model.addAttribute("sm",request.getSession().getAttribute("sm"));
		model.addAttribute("lastlogin", request.getSession().getAttribute("lastlogin"));
		model.addAttribute("userData" ,ce);
		if (ce!=null) {
			model.addAttribute("updatemessage" , "Profile updated Successfully");
			
		}
		
		return "Profileupdate";


	}

	@RequestMapping(value = "/getUserProfile" ,method = RequestMethod.GET)
	public String getUserProfile(HttpServletRequest request,Model model){
		List<CrendientialsEntity> UserEntity =	profileUpdateImp.getUserDataByName((String) request.getSession().getAttribute("sm"));
		model.addAttribute("userData" ,UserEntity);
		model.addAttribute("sm",request.getSession().getAttribute("sm"));
		model.addAttribute("lastlogin", request.getSession().getAttribute("lastlogin"));
		return "Profileupdate";
		
		
	}
	
	@RequestMapping(value = "/getLastLogedIn", method = RequestMethod.GET)
	public String getLastLogedIn(HttpServletRequest request , Model model) {
		return null;
		
		
		
	}
	
}
