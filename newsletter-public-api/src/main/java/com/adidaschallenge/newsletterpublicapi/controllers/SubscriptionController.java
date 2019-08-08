package com.adidaschallenge.newsletterpublicapi.controllers;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.adidaschallenge.newsletterpublicapi.beans.NewsletterSubscription;
import com.adidaschallenge.newsletterpublicapi.services.NewsletterPrivateApiService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(value = "/subscription")
@Api(value = "Public subscriptions API service")
public class SubscriptionController {
	
	private NewsletterPrivateApiService privateApiService;
	
	public SubscriptionController(NewsletterPrivateApiService privateApiService) {
		this.privateApiService = privateApiService;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Create a subscription", notes = "Public endpoint for creating a subscription to a newsletter")
    public String subscribe(@Valid @RequestBody NewsletterSubscription subscription) {
		privateApiService.subscribe(subscription);
		System.out.println("SubscribeRequestSent");
		return "OK";
    }
	
}
