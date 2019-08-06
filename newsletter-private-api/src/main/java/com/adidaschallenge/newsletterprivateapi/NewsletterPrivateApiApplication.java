package com.adidaschallenge.newsletterprivateapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.adidaschallenge.newsletterprivateapi.repositories"})
public class NewsletterPrivateApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsletterPrivateApiApplication.class, args);
	}

}
