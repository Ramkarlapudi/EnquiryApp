package com.ramkarlapudi.EnquiryApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.ramkarlapudi.EnquiryApp.entity.UserActivityEntity;

@EnableJpaRepositories
@Repository
public interface UserActivityRepository extends JpaRepository<UserActivityEntity, Integer> {

}
