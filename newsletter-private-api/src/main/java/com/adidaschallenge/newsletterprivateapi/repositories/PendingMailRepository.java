package com.adidaschallenge.newsletterprivateapi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.adidaschallenge.newsletterprivateapi.entities.PendingMail;

@Repository
public interface PendingMailRepository extends CrudRepository<PendingMail, Integer>{

}
