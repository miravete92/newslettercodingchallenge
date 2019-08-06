package com.adidaschallenge.newsletterprivateapi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.adidaschallenge.newsletterprivateapi.entities.Newsletter;

@Repository
public interface NewsletterRepository extends CrudRepository<Newsletter, Integer>{

}
