package com.adidaschallenge.newsletterprivateapi.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.adidaschallenge.newsletterprivateapi.beans.NewsletterSubscription;
import com.adidaschallenge.newsletterprivateapi.entities.Subscription;
import com.adidaschallenge.newsletterprivateapi.services.SubscriptionService;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(path="/subscription")
public class SubscriptionController {
	
	private SubscriptionService subscriptionService;
	
	public SubscriptionController(SubscriptionService subscriptionService) {
		this.subscriptionService = subscriptionService;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Create a subscription", notes = "Create a subscription to a newsletter")
    public ResponseEntity<Integer> createSubscription(@Valid @RequestBody NewsletterSubscription subscription) {
		Subscription newSubscription = this.subscriptionService.createOrUpdateSubscription(subscription);
		if(newSubscription!=null) return new ResponseEntity<Integer>(newSubscription.getId(), HttpStatus.OK);
		else return new ResponseEntity<Integer>(-1, HttpStatus.BAD_REQUEST);
    }
}
