package com.adidaschallenge.sendmailservice.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.adidaschallenge.sendmailservice.entites.PendingMail;

@Repository
public interface PendingMailRepository extends CrudRepository<PendingMail, Integer>{
	List<PendingMail> findBySent(boolean sent);
}
