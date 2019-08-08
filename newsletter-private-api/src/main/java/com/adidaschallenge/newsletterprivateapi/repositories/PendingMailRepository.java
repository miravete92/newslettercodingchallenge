package com.adidaschallenge.newsletterprivateapi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.adidaschallenge.newsletterprivateapi.entities.PendingMail;

// This interface should be shared with send-mail-service project. It should be extracted into a common artifact and referenced from both projects.
@Repository
public interface PendingMailRepository extends CrudRepository<PendingMail, Integer>{

}
