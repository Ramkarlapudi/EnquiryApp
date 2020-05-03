package com.ramkarlapudi.EnquiryApp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;
@Component
@Entity
@Table(name ="spring_session")
public class Spring_Session {
	
	@Id
	@Column(name = "PRIMARY_ID")
	private Character PRIMARY_ID;
	
	@Column(name = "SESSION_ID")
	private Character SESSION_ID;
	
	@Column(name = "CREATION_TIME")
	private Long CREATION_TIME;
	
	@Column(name = "LAST_ACCESS_TIME")
	private Long LAST_ACCESS_TIME;
	
	@Column(name = "MAX_INACTIVE_INTERVAL")
	private  int MAX_INACTIVE_INTERVAL;
	
	@Column(name = "EXPIRY_TIME")
	private Long EXPIRY_TIME;
	
	@Column(name = "PRINCIPAL_NAME")
	private String PRINCIPAL_NAME;

	public Character getPRIMARY_ID() {
		return PRIMARY_ID;
	}

	public void setPRIMARY_ID(Character pRIMARY_ID) {
		PRIMARY_ID = pRIMARY_ID;
	}

	public Character getSESSION_ID() {
		return SESSION_ID;
	}

	public void setSESSION_ID(Character sESSION_ID) {
		SESSION_ID = sESSION_ID;
	}

	public Long getCREATION_TIME() {
		return CREATION_TIME;
	}

	public void setCREATION_TIME(Long cREATION_TIME) {
		CREATION_TIME = cREATION_TIME;
	}

	public Long getLAST_ACCESS_TIME() {
		return LAST_ACCESS_TIME;
	}

	public void setLAST_ACCESS_TIME(Long lAST_ACCESS_TIME) {
		LAST_ACCESS_TIME = lAST_ACCESS_TIME;
	}

	public int getMAX_INACTIVE_INTERVAL() {
		return MAX_INACTIVE_INTERVAL;
	}

	public void setMAX_INACTIVE_INTERVAL(int mAX_INACTIVE_INTERVAL) {
		MAX_INACTIVE_INTERVAL = mAX_INACTIVE_INTERVAL;
	}

	public Long getEXPIRY_TIME() {
		return EXPIRY_TIME;
	}

	public void setEXPIRY_TIME(Long eXPIRY_TIME) {
		EXPIRY_TIME = eXPIRY_TIME;
	}

	public String getPRINCIPAL_NAME() {
		return PRINCIPAL_NAME;
	}

	public void setPRINCIPAL_NAME(String pRINCIPAL_NAME) {
		PRINCIPAL_NAME = pRINCIPAL_NAME;
	}
	
	
	
	
	
	
	

}
