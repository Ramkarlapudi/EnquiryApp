package com.ramkarlapudi.EnquiryApp.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramkarlapudi.EnquiryApp.entity.CrendientialsEntity;
import com.ramkarlapudi.EnquiryApp.entity.Spring_Session;
import com.ramkarlapudi.EnquiryApp.entity.UserActivityEntity;
import com.ramkarlapudi.EnquiryApp.model.UserRegistration;
import com.ramkarlapudi.EnquiryApp.repository.CrendientialsRepository;
import com.ramkarlapudi.EnquiryApp.repository.Spring_SessionRepository;
import com.ramkarlapudi.EnquiryApp.repository.UserActivityRepository;

@Service
public class ProfileUpdateImp implements ProfileUpdate {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private CrendientialsRepository crendientialsRepository;

	@Autowired
	private Spring_SessionRepository spring_SessionRepository;

	@Autowired
	private UserActivityRepository userActivityRepository;

	@Override
	public CrendientialsEntity updateUserData(UserRegistration userRegistration) {
		int userid = 0;
		List<CrendientialsEntity> userentity = getUserDataByName(userRegistration.getUsername());
		CrendientialsEntity crendientialsEntity = new CrendientialsEntity();
		for (CrendientialsEntity crendientialsEntity1 : userentity) {
			userid = crendientialsEntity1.getUser_id();
		}

		Optional<CrendientialsEntity> cre = crendientialsRepository.findById(userid);
		if (cre.isPresent()) {
			crendientialsEntity = cre.get();
			crendientialsEntity.setUser_name(userRegistration.getUsername());
			crendientialsEntity.setEmail_id(userRegistration.getEmailid());
			crendientialsEntity.setPhone(userRegistration.getPhone());
			crendientialsEntity.setState(userRegistration.getState());
			crendientialsEntity.setCity(userRegistration.getCity());
			crendientialsEntity.setAddress(userRegistration.getAddress());
			crendientialsEntity.setPincode(userRegistration.getPincode());
			crendientialsEntity.setPassword(userRegistration.getPassword());

			crendientialsEntity = crendientialsRepository.save(crendientialsEntity);
		}
		return crendientialsEntity;

	}

	public List<CrendientialsEntity> getUserDataByName(String username) {
		Query q = em.createNamedQuery("getUserDataByName", CrendientialsEntity.class);
		q.setParameter(1, username);
		List<CrendientialsEntity> userEntity = q.getResultList();
		CrendientialsEntity ccc = userEntity.get(0);

		return userEntity;

	}

	public void SpringSessionToUA(HttpServletRequest request) {
		int userid = 0;
		List<CrendientialsEntity> userentity = getUserDataByName((String) request.getSession().getAttribute("sm"));
		for (CrendientialsEntity crendientialsEntity : userentity) {
			userid = crendientialsEntity.getUser_id();
		}
		List<Spring_Session> session = spring_SessionRepository.findAll();
		List<UserActivityEntity> userActivity = userActivityRepository.findAll();
		UserActivityEntity UAE = new UserActivityEntity();

		Spring_Session spsession = session.get(0);

		Timestamp timestampLastAccesTime = new Timestamp(spsession.getLAST_ACCESS_TIME());
		Timestamp timestampCreationTime = new Timestamp(spsession.getCREATION_TIME());
		String str1 = timestampLastAccesTime.toString();
		String str2 = timestampCreationTime.toString();
		for (UserActivityEntity userActivityEntity : userActivity) {
			if (userActivityEntity.getUser_id() == userid) {
				Query q = em.createNamedQuery("SessionUpdate", UserActivityEntity.class);
				q.setParameter(1, str1);
				q.setParameter(2, userid);

			} else {
				UAE.setUser_id(userid);
				UAE.setLAST_ACCESS_TIME(str1);
				UAE.setCREATION_TIME(str2);
				userActivityRepository.save(UAE);

			}
		}

	}

	public String getLastLogedIn(HttpServletRequest request) {
		int userid = 0;
		List<CrendientialsEntity> userentity = getUserDataByName((String) request.getSession().getAttribute("sm"));
		for (CrendientialsEntity crendientialsEntity : userentity) {
			userid = crendientialsEntity.getUser_id();
		}

		Query q = em.createNamedQuery("getSession", UserActivityEntity.class);
		q.setParameter(1, userid);
		List<UserActivityEntity> user = q.getResultList();
		UserActivityEntity userAct = user.get(0);

		return userAct.getLAST_ACCESS_TIME();

	}

	public void createLastLogin(HttpServletRequest request) {

		int userid = 0;
		List<CrendientialsEntity> userentity = getUserDataByName(
				(String) request.getSession().getAttribute("registersession"));
		for (CrendientialsEntity crendientialsEntity : userentity) {

			userid = crendientialsEntity.getUser_id();
			System.out.println("userid  " + userid);

		}
		UserActivityEntity UAE = new UserActivityEntity();
		UAE.setUser_id(userid);
		UAE.setLAST_ACCESS_TIME("NA");
		UAE.setCREATION_TIME("NA");
		userActivityRepository.save(UAE);

	}

}
