package com.adidaschallenge.sendmailservice;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.adidaschallenge.sendmailservice.entites.PendingMail;
import com.adidaschallenge.sendmailservice.repositories.PendingMailRepository;


@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    
    @Autowired
	private PendingMailRepository pendingMailRepository;
    
    @Autowired
    private JavaMailSender sender;
    
    /*@Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
    }*/
    
    @Scheduled(fixedDelay = 5000)
    public void sendPendingMails() {
        log.info("The time is now {}", dateFormat.format(new Date()));
        pendingMailRepository.findBySent(false).forEach(pendingMail -> {
        	try {
            	sendPendingMail(pendingMail);
            	pendingMail.setSent(true);
            	pendingMailRepository.save(pendingMail);
            }
            catch(Exception e) {
            	e.printStackTrace();
            }
        }); 
    }
    
    private void sendPendingMail(PendingMail pendingMail) throws Exception{
    	log.info("Sending to "+pendingMail.getReceiver());
    	MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
         
        helper.setTo(pendingMail.getReceiver());
        helper.setText(pendingMail.getMailContent(), true);
        helper.setSubject("ADIDAS CHALLENGE");
         
        sender.send(message);
    }
}
