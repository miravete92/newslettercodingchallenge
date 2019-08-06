package com.adidaschallenge.sendmailservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SendMailServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SendMailServiceApplication.class, args);
	}

}
