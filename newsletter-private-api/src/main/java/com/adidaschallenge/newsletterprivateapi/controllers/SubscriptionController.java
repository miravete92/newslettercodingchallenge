package com.adidaschallenge.newsletterprivateapi.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.adidaschallenge.newsletterprivateapi.beans.NewsletterSubscription;
import com.adidaschallenge.newsletterprivateapi.entities.Newsletter;
import com.adidaschallenge.newsletterprivateapi.entities.PendingMail;
import com.adidaschallenge.newsletterprivateapi.entities.Subscription;
import com.adidaschallenge.newsletterprivateapi.repositories.NewsletterRepository;
import com.adidaschallenge.newsletterprivateapi.repositories.PendingMailRepository;
import com.adidaschallenge.newsletterprivateapi.repositories.SubscriptionRepository;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(path="/subscription")
public class SubscriptionController {
	
	@Autowired
	private SubscriptionRepository subscriptionRepository;
	@Autowired
	private PendingMailRepository pendingMailRepository;
	@Autowired
	private NewsletterRepository newsletterRepository;
	
	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Create a subscription", notes = "Create a subscription to a newsletter")
    public int createSubscription(@Valid @RequestBody NewsletterSubscription subscription) {
		Newsletter newsletter = newsletterRepository.findById(subscription.getNewsletterId()).orElse(null);
		
		if(newsletter!=null) {
			if(subscriptionRepository.findByEmail(subscription.getEmail())!=null) {
				return -1;
			}
			Subscription newSubscription = new Subscription();
			newSubscription.setFirstName(subscription.getFirstName());
			newSubscription.setEmail(subscription.getEmail());
			newSubscription.setDateOfBirth(subscription.getDateOfBirth());
			newSubscription.setConsent(subscription.isConsent());
			newSubscription.setGender(subscription.getGender());
			newSubscription.setNewsletterId(subscription.getNewsletterId());
			newSubscription = subscriptionRepository.save(newSubscription);
			
			PendingMail newPendingMail = new PendingMail();
			newPendingMail.setReceiver(newSubscription.getEmail());
			newPendingMail.setMailContent(newsletter.getWelcomeMail());
			newPendingMail.setSent(false);
			newPendingMail = pendingMailRepository.save(newPendingMail);
			
			return newSubscription.getId();
		}
		else {
			return -1;
		}
    }
}
