package com.ramkarlapudi.EnquiryApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramkarlapudi.EnquiryApp.entity.CrendientialsEntity;
import com.ramkarlapudi.EnquiryApp.model.UserRegistration;
import com.ramkarlapudi.EnquiryApp.repository.CrendientialsRepository;

@Service
public class UserRegistrationServiceImp implements UserRegistrationService {

	@Autowired
	private CrendientialsRepository crendientialsRepository;

	@Autowired
	private UserRegistration userRegistration;

	// String Username, String Password, String Emailid, Long Phone, String Address,
	// String State, String City, int Pincode

	@Override
	public Boolean UserRegister( UserRegistration userRegistration    ) {

		CrendientialsEntity ce = new CrendientialsEntity();

		ce.setUser_name(userRegistration.getUsername());
		ce.setPassword(userRegistration.getPassword());
		ce.setEmail_id(userRegistration.getEmailid());
		ce.setPhone(userRegistration.getPhone());
		ce.setAddress(userRegistration.getAddress());
		ce.setState(userRegistration.getState());
		ce.setCity(userRegistration.getCity());
		ce.setPincode(userRegistration.getPincode());
		ce = crendientialsRepository.saveAndFlush(ce);
		if (ce != null) {
			return true;
		}
		// TODO Auto-generated method stub
		return false;
	}

}
