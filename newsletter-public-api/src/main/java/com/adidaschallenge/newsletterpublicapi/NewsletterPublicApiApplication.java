package com.adidaschallenge.newsletterpublicapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableAsync
@EnableScheduling
@Import(SwaggerConfiguration.class)
public class NewsletterPublicApiApplication implements WebMvcConfigurer {

	// Spring boot application starter
	public static void main(String[] args) {
		SpringApplication.run(NewsletterPublicApiApplication.class, args);
	}

	// Configure swagger-ui
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
	}

}
