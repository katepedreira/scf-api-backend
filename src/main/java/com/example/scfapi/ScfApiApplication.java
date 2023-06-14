package com.example.scfapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ScfApiApplication {

	public static void main(String[] args) {

		SpringApplication.run(ScfApiApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("12345"));

	}

}
