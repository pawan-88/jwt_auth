package com.jwt.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JwtAuthencationApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtAuthencationApplication.class, args);

		System.out.println("hello");
	}

}
