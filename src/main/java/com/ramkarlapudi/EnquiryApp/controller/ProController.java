package com.ramkarlapudi.EnquiryApp.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.ramkarlapudi.EnquiryApp.model.Credientials;
import com.ramkarlapudi.EnquiryApp.model.SearchData;
import com.ramkarlapudi.EnquiryApp.model.UserRegistration;
import com.ramkarlapudi.EnquiryApp.service.Covid19Imp;
import com.ramkarlapudi.EnquiryApp.service.LoginValidateImplementation;
import com.ramkarlapudi.EnquiryApp.service.UserRegistrationServiceImp;

import okhttp3.Response;

@Controller
public class ProController {

	@Autowired(required = true)
	private LoginValidateImplementation LoginValidateImplementation;

	@Autowired
	private UserRegistrationServiceImp userRegistrationServiceImp;

	/*
	 * @Autowired private Covid19 covid19;
	 */

	@RequestMapping(value = "/index")
	public String fun() {
		return "index";
	}

	@RequestMapping(value = "/Login")
	public String LoginPage() {
		
		System.out.println("Her RAMA kksca ajsca sci ");
		
		return "Login";
	}

	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	public String ValidateLoginPage(@ModelAttribute("Credientials") Credientials credientials, Model model) {

		// LoginValidateImplementation.validate(credientials.getUsername(),
		// credientials.getPassword());

		LoginValidateImplementation.validateLogin1(credientials.getUsername());

		if (credientials.getPassword().equals(LoginValidateImplementation.validateLogin1(credientials.getUsername()))) {
			model.addAttribute("sm", credientials.getUsername());
			return "EnquiryPage";

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

	@RequestMapping(value = "/World")
	public String enquiryPage() {
		return "EnquiryPage";
	}

	@RequestMapping(value = "/result")
	public String getWorldData(Model model) throws UnsupportedEncodingException, UnirestException {
		Covid19Imp covid19 = new Covid19Imp();
		HashMap<String, String> Hmap = covid19.getAllData("ALL");
		model.addAttribute("CovidData", Hmap);

		return "EnquiryPage";
	}

	@RequestMapping(value = "/World", method = RequestMethod.POST)
	public String covid19(@ModelAttribute("SearchData") SearchData searchData, Model model)
			throws IOException, UnirestException {
		Covid19Imp covid19 = new Covid19Imp();
		System.out.println("serch input " + searchData.getALL());
		if (searchData.getALL().equals("ALL")) {
			HashMap<String, String> Hmap = covid19.getAllData(searchData.getALL());
			model.addAttribute("CovidData", Hmap);
		} else {
			HashMap<String, String> Hmap = covid19.getCountryWiseData(searchData.getALL());
			model.addAttribute("CovidData", Hmap);
		}

		return "EnquiryPage";

	}
}
