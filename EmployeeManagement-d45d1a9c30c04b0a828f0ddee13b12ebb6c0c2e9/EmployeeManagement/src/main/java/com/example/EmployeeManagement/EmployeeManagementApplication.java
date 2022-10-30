package com.example.EmployeeManagement;

import com.example.EmployeeManagement.JwtFilter.SecurityFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.FilterRegistration;

@SpringBootApplication
public class EmployeeManagementApplication {

	@Bean
	public FilterRegistrationBean jwtFilter(){
		FilterRegistrationBean obj = new FilterRegistrationBean();
		obj.setFilter(new SecurityFilter());
		obj.addUrlPatterns("/api/v1/*");
		return obj;
	}
	public static void main(String[] args) {

		SpringApplication.run(EmployeeManagementApplication.class, args);
	}

}
