package com.ramkarlapudi.EnquiryApp.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.AbstractQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.ramkarlapudi.EnquiryApp.entity.CrendientialsEntity;
import com.ramkarlapudi.EnquiryApp.repository.CrendientialsRepository;


@Service
public class LoginValidateImplementation  implements LoginValidate{
	
	/*
	 * @Autowired private CrendientialsEntity crendientialsEntity;
	 */

	
	@Autowired
	private CrendientialsRepository crendientialsRepository;
	
	@PersistenceContext
	private EntityManager em;	
	
	
	
	
	/*
	 * @Override public Boolean validate(String Username, String Password) { // TODO
	 * Auto-generated method stub CrendientialsEntity crendientialsEntity = new
	 * CrendientialsEntity(); crendientialsEntity.setUser_name(Username);
	 * System.out.println( "service " +crendientialsEntity.getUser_name());
	 * Example<CrendientialsEntity> exmpl = Example.of(crendientialsEntity);
	 * Optional<CrendientialsEntity> CE = crendientialsRepository.findOne(exmpl);
	 * if(CE.isPresent()){ return true; } validateLogin(Username,Password);
	 * 
	 * validateLogin1(Username);
	 * 
	 * return false; }
	 * 
	 * @Override public Boolean validateLogin(String Username, String Password) {
	 * 
	 * CriteriaBuilder cb = em.getCriteriaBuilder();
	 * CriteriaQuery<CrendientialsEntity> cquery =
	 * cb.createQuery(CrendientialsEntity.class); Root<CrendientialsEntity> Centity
	 * = cquery.from(CrendientialsEntity.class); cquery.select(Centity);
	 * TypedQuery<CrendientialsEntity> typedQuery = em.createQuery(cquery);
	 * List<CrendientialsEntity> Data = typedQuery.getResultList(); for
	 * (CrendientialsEntity crendientialsEntity : Data) {
	 * System.out.println("Data  " + crendientialsEntity.getUser_id()); } return
	 * null; }
	 */
	
	public String validateLogin1(String Username) {
		String ps =null;
		CriteriaBuilder cb = em.getCriteriaBuilder();
		AbstractQuery<CrendientialsEntity> cquery = cb.createQuery(CrendientialsEntity.class);
		Root<CrendientialsEntity> Centity = cquery.from(CrendientialsEntity.class);
		cquery.where(cb.equal(Centity.get("User_name"),Username));
		CriteriaQuery<CrendientialsEntity> select1 =(( CriteriaQuery<CrendientialsEntity>) cquery).select(Centity);
		TypedQuery<CrendientialsEntity> typedQuery = em.createQuery(select1);
		List<CrendientialsEntity> Flist = typedQuery.getResultList();
		for (CrendientialsEntity crendientialsEntity : Flist) {
		System.out.println("Password for is" + crendientialsEntity.getPassword());;
		 ps = crendientialsEntity.getPassword();
		}
		return ps;
	}
	
	public Boolean RegisterUser() {
		
		
		
		return null;
		
		
	}
	
	
	
	


}
