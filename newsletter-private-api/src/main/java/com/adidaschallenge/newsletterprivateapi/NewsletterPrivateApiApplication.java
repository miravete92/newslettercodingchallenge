package com.adidaschallenge.newsletterprivateapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.adidaschallenge.newsletterprivateapi.repositories"})
@Import(SwaggerConfiguration.class)
public class NewsletterPrivateApiApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(NewsletterPrivateApiApplication.class, args);
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
	}

}
