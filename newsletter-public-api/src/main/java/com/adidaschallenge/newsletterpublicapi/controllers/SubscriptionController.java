package com.adidaschallenge.newsletterpublicapi.controllers;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.adidaschallenge.common.beans.NewsletterSubscription;
import com.adidaschallenge.newsletterpublicapi.services.NewsletterPrivateApiService;


@RestController
public class SubscriptionController {
	
	private NewsletterPrivateApiService privateApiService;
	
	public SubscriptionController(NewsletterPrivateApiService privateApiService) {
		this.privateApiService = privateApiService;
	}
	@RequestMapping(method = RequestMethod.POST, value = "/subscription")
    public String subscribe(@Valid @RequestBody NewsletterSubscription subscription) {
		privateApiService.subscribe(subscription);
		System.out.println("SubscribeRequestSent");
		return "OK";
    }
	
}
