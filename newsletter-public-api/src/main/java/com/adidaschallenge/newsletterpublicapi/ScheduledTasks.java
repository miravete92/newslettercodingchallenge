package com.adidaschallenge.newsletterpublicapi;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.adidaschallenge.newsletterpublicapi.services.NewsletterPrivateApiService;


@Component
public class ScheduledTasks {

    private NewsletterPrivateApiService privateApiService;
	
	public ScheduledTasks(NewsletterPrivateApiService privateApiService) {
		this.privateApiService = privateApiService;
	}
    
    @Scheduled(fixedDelay = 20000)
    public void sendPendingSubscriptionsToServer() {
    	privateApiService.sendPendingSubscriptionsToServer();
    }
   
}
