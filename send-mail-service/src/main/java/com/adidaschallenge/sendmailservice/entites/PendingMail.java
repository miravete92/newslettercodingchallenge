package com.adidaschallenge.sendmailservice.entites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//This class should be shared with send pending mails project, extracting it into a maven common project
@Entity
public class PendingMail {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String receiver;

    // Should be a reference to an email entity containing the subject, the content and some extra parameters
    private String mailContent;
    
    private boolean sent;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getMailContent() {
		return mailContent;
	}

	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}

	public boolean isSent() {
		return sent;
	}

	public void setSent(boolean sent) {
		this.sent = sent;
	}
    
    
}
