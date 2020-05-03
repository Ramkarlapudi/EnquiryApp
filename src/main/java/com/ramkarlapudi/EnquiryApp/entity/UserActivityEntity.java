package com.ramkarlapudi.EnquiryApp.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "useractivity")

@NamedNativeQueries({

	@NamedNativeQuery(name = "SessionUpdate", query = "update UserActivity set LAST_ACCESS_TIME = ?" + "WHERE User_id = ?", resultClass = UserActivityEntity.class),
	@NamedNativeQuery(name = "getSession", query = "select * from useractivity " + " WHERE User_id = ?", resultClass = UserActivityEntity.class)
	 })

public class UserActivityEntity {

	@Id
	@Column(name = "User_id")
	private int User_id;

	@Column(name = "CREATION_TIME")
	private String CREATION_TIME;

	@Column(name = "LAST_ACCESS_TIME")
	private String LAST_ACCESS_TIME;

	@OneToOne
	// @MapsId
	@JoinColumn(name = "User_id")
	private CrendientialsEntity crendientialsEntity;

	public int getUser_id() {
		return User_id;
	}

	public void setUser_id(int user_id) {
		User_id = user_id;
	}

	public String getCREATION_TIME() {
		return CREATION_TIME;
	}

	public void setCREATION_TIME(String cREATION_TIME) {
		CREATION_TIME = cREATION_TIME;
	}

	public String getLAST_ACCESS_TIME() {
		return LAST_ACCESS_TIME;
	}

	public void setLAST_ACCESS_TIME(String lAST_ACCESS_TIME) {
		LAST_ACCESS_TIME = lAST_ACCESS_TIME;
	}

	public CrendientialsEntity getCrendientialsEntity() {
		return crendientialsEntity;
	}

	public void setCrendientialsEntity(CrendientialsEntity crendientialsEntity) {
		this.crendientialsEntity = crendientialsEntity;
	}

	

}
