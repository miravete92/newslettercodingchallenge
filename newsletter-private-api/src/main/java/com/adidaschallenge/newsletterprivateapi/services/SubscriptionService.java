package com.adidaschallenge.newsletterprivateapi.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adidaschallenge.newsletterprivateapi.beans.NewsletterSubscription;
import com.adidaschallenge.newsletterprivateapi.entities.Newsletter;
import com.adidaschallenge.newsletterprivateapi.entities.PendingMail;
import com.adidaschallenge.newsletterprivateapi.entities.Subscription;
import com.adidaschallenge.newsletterprivateapi.repositories.NewsletterRepository;
import com.adidaschallenge.newsletterprivateapi.repositories.PendingMailRepository;
import com.adidaschallenge.newsletterprivateapi.repositories.SubscriptionRepository;

@Service
public class SubscriptionService {
	
	@Autowired
	private SubscriptionRepository subscriptionRepository;
	@Autowired
	private PendingMailRepository pendingMailRepository;
	@Autowired
	private NewsletterRepository newsletterRepository;
	
	@Transactional
	public Subscription createOrUpdateSubscription(NewsletterSubscription subscription) {
		Newsletter newsletter = newsletterRepository.findById(subscription.getNewsletterId()).orElse(null);
		
		if(newsletter!=null) {
			Subscription existingSubscription = subscriptionRepository.findByEmailAndNewsletterId(subscription.getEmail(), subscription.getNewsletterId());
			if(existingSubscription!=null) {
				BeanUtils.copyProperties(subscription, existingSubscription);
				existingSubscription = subscriptionRepository.save(existingSubscription);
				return existingSubscription;
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
				
				return newSubscription;
			}
		}
		else {
			return null;
		}
	}
}
