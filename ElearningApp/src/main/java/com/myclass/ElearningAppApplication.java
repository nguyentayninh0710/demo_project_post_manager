package com.myclass;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;



@SpringBootApplication
@ComponentScan(basePackages  = "com.myclass")
public class ElearningAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElearningAppApplication.class, args);
	}
	
}
