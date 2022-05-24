package com.apiAP.app;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;



@SpringBootApplication
@EnableEncryptableProperties
public class ApiApApplication {
	
	public static void main(String[] args) {
		
		SpringApplication.run(ApiApApplication.class, args);
		
	
	
		}
	

	



}
