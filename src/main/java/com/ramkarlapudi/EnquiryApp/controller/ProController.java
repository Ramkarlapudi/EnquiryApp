package com.ramkarlapudi.EnquiryApp.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.ramkarlapudi.EnquiryApp.model.Credientials;
import com.ramkarlapudi.EnquiryApp.model.SearchData;
import com.ramkarlapudi.EnquiryApp.model.UserRegistration;
import com.ramkarlapudi.EnquiryApp.service.Covid19Imp;
import com.ramkarlapudi.EnquiryApp.service.LoginValidateImplementation;
import com.ramkarlapudi.EnquiryApp.service.ProfileUpdateImp;
import com.ramkarlapudi.EnquiryApp.service.UserRegistrationServiceImp;

@Controller
public class ProController {

	@Autowired(required = true)
	private LoginValidateImplementation LoginValidateImplementation;

	@Autowired
	private UserRegistrationServiceImp userRegistrationServiceImp;

	@Autowired
	private ProfileUpdateImp profileUpdateImp;

	/*
	 * @Autowired private Covid19 covid19;
	 */

	@RequestMapping(value = "/index")
	public String fun() {
		return "index";
	}

	@RequestMapping(value = "/EnquiryPage")
	public String Enquirypage(HttpServletRequest request, Model model) {
		model.addAttribute("sm", request.getSession().getAttribute("sm"));
		model.addAttribute("lastlogin", request.getSession().getAttribute("lastlogin"));
		return "EnquiryPage";
	}

	@RequestMapping(value = "/Home")
	public String fun2() {
		return "Home";
	}

	@RequestMapping(value = "/Login")
	public String LoginPage() {

		return "Login";
	}

	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	public String ValidateLoginPage(@ModelAttribute("Credientials") Credientials credientials, Model model,
			HttpServletRequest request) {

		// LoginValidateImplementation.validate(credientials.getUsername(),
		// credientials.getPassword());

		LoginValidateImplementation.validateLogin1(credientials.getUsername());

		if (credientials.getPassword().equals(LoginValidateImplementation.validateLogin1(credientials.getUsername()))) {
			model.addAttribute("sm", credientials.getUsername());
			request.getSession().setAttribute("sm", credientials.getUsername());
			String str = profileUpdateImp.getLastLogedIn(request);
			request.getSession().setAttribute("lastlogin", str);
			model.addAttribute("lastlogin", str);
			model.addAttribute("lastlogin", str);
			return "EnquiryPage";

		} else {
			model.addAttribute("msg", true);
			return "Login";
		}

	}

	@RequestMapping(value = "/Register", method = RequestMethod.POST)
	public String userRegistration(@ModelAttribute("Registration") UserRegistration userRegistration, Model model,
			HttpServletRequest request) {

		System.out.println();

		if (userRegistrationServiceImp.UserRegister(userRegistration)) {
			request.getSession().setAttribute("registersession", userRegistration.getUsername());
			profileUpdateImp.createLastLogin(request);
			return "Login";
		}

		return "index";

	}

	@RequestMapping(value = "/World")
	public String enquiryPage(HttpServletRequest request, Model model) {
		System.out.println("checking      " + request.getSession().getAttribute("sm"));
		if (request.getSession().getAttribute("sm") != null) {
			model.addAttribute("sm", request.getSession().getAttribute("sm"));
			return "EnquiryPage";
		}
		return "Login";
	}

	@RequestMapping(value = "/result")
	public String getWorldData(Model model) throws UnsupportedEncodingException, UnirestException {
		Covid19Imp covid19 = new Covid19Imp();
		HashMap<String, Long> Hmap = covid19.getAllData("ALL");
		model.addAttribute("CovidData", Hmap);

		return "EnquiryPage";
	}

	@RequestMapping(value = "/World", method = RequestMethod.POST)
	public String covid19(@ModelAttribute("SearchData") SearchData searchData, Model model)
			throws IOException, UnirestException {
		Covid19Imp covid19 = new Covid19Imp();
		System.out.println("serch input " + searchData.getALL());
		if (searchData.getALL().equals("ALL")) {
			HashMap<String, Long> Hmap = covid19.getAllData(searchData.getALL());
			model.addAttribute("CovidData", Hmap);
		} else {
			HashMap<String, String> Hmap = covid19.getCountryWiseData(searchData.getALL());
			model.addAttribute("CovidData", Hmap);
		}

		return "EnquiryPage";

	}

	@RequestMapping(value = "/destroy", method = RequestMethod.POST)
	public String destroySession(HttpServletRequest request) {
		profileUpdateImp.SpringSessionToUA(request);

		System.out.println("Destroy session  " + request.getSession().getAttribute("sm"));

		request.getSession().removeAttribute("sm");
		request.getSession().invalidate();
		System.out.println("Destroy Session  " + request.getSession().getAttribute("sm"));
		return "Login";
	}

	@RequestMapping(value = "/Profileupdate")
	public String Profileupdate() {
		return "Profileupdate";

	}
}
