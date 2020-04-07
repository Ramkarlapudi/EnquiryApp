package com.ramkarlapudi.EnquiryApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ramkarlapudi.EnquiryApp.model.Credientials;
import com.ramkarlapudi.EnquiryApp.model.UserRegistration;
import com.ramkarlapudi.EnquiryApp.service.LoginValidateImplementation;
import com.ramkarlapudi.EnquiryApp.service.UserRegistrationServiceImp;

@Controller
public class ProController {

	@Autowired(required = true)
	private LoginValidateImplementation LoginValidateImplementation;

	@Autowired
	private UserRegistrationServiceImp userRegistrationServiceImp;

	@RequestMapping(value = "/index")
	public String fun() {
		return "index";
	}

	@RequestMapping(value = "/Login")
	public String LoginPage() {
		return "Login";
	}

	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	public String ValidateLoginPage(@ModelAttribute("Credientials") Credientials credientials, Model model) {

		// LoginValidateImplementation.validate(credientials.getUsername(),
		// credientials.getPassword());

		LoginValidateImplementation.validateLogin1(credientials.getUsername());

		if (credientials.getPassword().equals(LoginValidateImplementation.validateLogin1(credientials.getUsername()))) {
			model.addAttribute("sm", credientials.getUsername());
			return "Home.html";

		} else {
			model.addAttribute("msg", true);
			return "Login";
		}

	}

	@RequestMapping(value = "/Register", method = RequestMethod.POST)
	public String userRegistration(@ModelAttribute("Registration") UserRegistration userRegistration, Model model) {
		
		System.out.println();

		if (userRegistrationServiceImp.UserRegister(userRegistration)) {
			return "Login";
		}

		return "index";

	}
}
