package com.adidaschallenge.sendmailservice.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.adidaschallenge.sendmailservice.entites.PendingMail;

//This interface should be shared with newsletter-private-api project. It should be extracted into a common artifact and referenced from both projects.
@Repository
public interface PendingMailRepository extends CrudRepository<PendingMail, Integer>{
	List<PendingMail> findBySent(boolean sent);
}
