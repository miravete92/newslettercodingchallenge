package com.adidaschallenge.newsletterprivateapi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.adidaschallenge.newsletterprivateapi.entities.Subscription;

@Repository
public interface SubscriptionRepository extends CrudRepository<Subscription, Integer>{
	Subscription findByEmailAndNewsletterId(String email, int newsletterId);
}
