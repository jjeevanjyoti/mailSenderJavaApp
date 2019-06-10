package com.assignment.springboot.mongo.controller;
 
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.*;  
import javax.mail.*;  
import javax.mail.internet.*;  
import javax.activation.*;  

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.assignment.springboot.mongo.model.Employee;
import com.assignment.springboot.mongo.service.Employeeservice;
 
@RestController
@RequestMapping(value= "/api/mongo/emp")
public class Employeecontroller  {
 
    @Autowired
    Employeeservice serv;
 
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
 
    ExecutorService executor= Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    
    
    @PostMapping(value= "/sendmaildata")
    public String create(@RequestBody String[] data) {	
		List<String> emails = Arrays.asList(data);
		int count=0;
		int lengthcount=emails.size();
        	for (String element: emails) {
                executor.execute(new MyRunnable(element));
                count++;
        	}
        	
        return "email sent successfully";
    }
}

class MyRunnable implements Runnable{
    String email;
    public MyRunnable(String element){
        this.email = element;
    }
    public void run(){
        try{
        	final String username = "jjeevanjyoti@gmail.com";
            final String password = "cxwihnvtskirudcx";
            Properties prop = new Properties();
    		prop.put("mail.smtp.host", "smtp.gmail.com");
            prop.put("mail.smtp.port", "587");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.starttls.enable", "true"); //TLS
            Session session = Session.getInstance(prop,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });
        	Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("jjeevanjyoti@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(this.email)
            );
            message.setSubject("Testing Gmail TLS");
            message.setText("Dear Stranger,"
                    + "\n\n Please do not spam my email!");

            Transport.send(message);

            System.out.println("Done");
        }catch(Exception err){
            err.printStackTrace();
        }
    }
}