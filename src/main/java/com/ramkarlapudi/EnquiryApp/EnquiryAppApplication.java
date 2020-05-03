package com.ramkarlapudi.EnquiryApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
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
public class EnquiryAppApplication  extends    SpringBootServletInitializer     {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(EnquiryAppApplication.class);
	}


	public static void main(String[] args) {
		
		SpringApplication.run(EnquiryAppApplication.class, args);
	}

}
