package com.ramkarlapudi.EnquiryApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import com.ramkarlapudi.EnquiryApp.entity.CrendientialsEntity;

@EnableJpaRepositories
@Repository
public interface CrendientialsRepository extends JpaRepository<CrendientialsEntity, Integer>,QueryByExampleExecutor<CrendientialsEntity> {
	

}
