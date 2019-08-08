package com.adidaschallenge.newsletterpublicapi.services;

import java.net.URI;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.adidaschallenge.newsletterpublicapi.beans.NewsletterSubscription;


@Service
public class NewsletterPrivateApiService {
	private final RestTemplate restTemplate;
	
	private BlockingQueue<NewsletterSubscription> pendingSubscriptionsQueue;
	
	@Value("${com.adidaschallenge.newsletterpublicapi.privateapipath}")
	private String privateApiPath;
	
	public NewsletterPrivateApiService(RestTemplateBuilder restTemplateBuilder) {
		this.pendingSubscriptionsQueue = new LinkedBlockingQueue<NewsletterSubscription>();
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
		catch(HttpClientErrorException e) {
			System.out.println("SubscribeRequestFailed: " + e.getStatusCode().toString());
			return CompletableFuture.completedFuture(-1);
		}
		catch(Exception e) {
			System.out.println("SubscribeRequestFailed: "+e.getMessage());
			System.out.println("Added to pending subscriptions");
			this.pendingSubscriptionsQueue.add(subscription);
			return CompletableFuture.completedFuture(-1);
		}
		
    }
	
	public void sendPendingSubscriptionsToServer() {
		if(this.pendingSubscriptionsQueue.size()>0) {
			System.out.println("Sending pending subscriptions to the server: "+this.pendingSubscriptionsQueue.size());
			NewsletterSubscription subscription = this.pendingSubscriptionsQueue.poll();
			if(subscription!=null) this.subscribe(subscription);
		}
	}
}
