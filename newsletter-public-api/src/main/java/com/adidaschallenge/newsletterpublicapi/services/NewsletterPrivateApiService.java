package com.adidaschallenge.newsletterpublicapi.services;

import java.net.URI;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.adidaschallenge.newsletterpublicapi.beans.NewsletterSubscription;


@Service
public class NewsletterPrivateApiService {
	private final RestTemplate restTemplate;
	
	@Value("${com.adidaschallenge.newsletterpublicapi.privateapipath}")
	private String privateApiPath;
	
	public NewsletterPrivateApiService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}
	
	@Async
    public CompletableFuture<Integer> subscribe(NewsletterSubscription subscription) {
		try {
			System.out.println(URI.create(privateApiPath+"/subscription").toString());
			Integer apiResult = restTemplate.postForObject(URI.create(privateApiPath+"/subscription"), 
					subscription, Integer.class);
			System.out.println("SubscribeRequestReceived");
	        return CompletableFuture.completedFuture(apiResult);
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("SubscribeRequestFailed");
			return CompletableFuture.completedFuture(-1);
		}
		
    }
}
