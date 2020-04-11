package com.ramkarlapudi.EnquiryApp.service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import com.mashape.unirest.http.exceptions.UnirestException;

public interface Covid19 {
	
	public HashMap<String, String> getCountryWiseData(String ALL) throws UnsupportedEncodingException, UnirestException;

}
