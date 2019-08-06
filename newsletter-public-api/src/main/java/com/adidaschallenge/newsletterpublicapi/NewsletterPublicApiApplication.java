package com.adidaschallenge.newsletterpublicapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class NewsletterPublicApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsletterPublicApiApplication.class, args);
	}

}
