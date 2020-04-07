package com.ramkarlapudi.EnquiryApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author CHAITANYA
 *
 */
@SpringBootApplication
@ComponentScan({ "com.ramkarlapudi.EnquiryApp.*"})
@Configuration
@EnableAutoConfiguration
@EntityScan("com.ramkarlapudi.EnquiryApp.entity")
public class EnquiryAppApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(EnquiryAppApplication.class, args);
	}

}
