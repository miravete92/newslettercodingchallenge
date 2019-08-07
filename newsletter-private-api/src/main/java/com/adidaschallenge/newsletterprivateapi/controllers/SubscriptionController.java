package com.adidaschallenge.newsletterprivateapi.controllers;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Integer> createSubscription(@Valid @RequestBody NewsletterSubscription subscription) {
		Newsletter newsletter = newsletterRepository.findById(subscription.getNewsletterId()).orElse(null);
		
		if(newsletter!=null) {
			Subscription existingSubscription = subscriptionRepository.findByEmailAndNewsletterId(subscription.getEmail(), subscription.getNewsletterId());
			if(existingSubscription!=null) {
				BeanUtils.copyProperties(subscription, existingSubscription);
				existingSubscription = subscriptionRepository.save(existingSubscription);
				return new ResponseEntity<Integer>(existingSubscription.getId(), HttpStatus.OK);
			}
			else {
				Subscription newSubscription = new Subscription();
				BeanUtils.copyProperties(subscription, newSubscription);
				newSubscription = subscriptionRepository.save(newSubscription);
				
				PendingMail newPendingMail = new PendingMail();
				newPendingMail.setReceiver(newSubscription.getEmail());
				newPendingMail.setMailContent(newsletter.getWelcomeMail());
				newPendingMail.setSent(false);
				newPendingMail = pendingMailRepository.save(newPendingMail);
				
				return new ResponseEntity<Integer>(newSubscription.getId(), HttpStatus.OK);
			}
		}
		else {
			return new ResponseEntity<Integer>(-1, HttpStatus.BAD_REQUEST);
		}
    }
}
