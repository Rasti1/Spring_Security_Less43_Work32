package com.example.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class SpringSecurityLess43Work32Application {

	private static final Logger LOGGER =
			Logger.getLogger(SpringSecurityLess43Work32Application.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityLess43Work32Application.class, args);
		LOGGER.info("APP is running...");
	}

}
