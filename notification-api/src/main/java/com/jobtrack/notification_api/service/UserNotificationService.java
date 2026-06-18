package com.jobtrack.notification_api.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;


/*
* avaMailSender is Spring Boot's mail abstraction built on top of the Java Mail API.
* It allows an application to send emails through an SMTP server.
*  In my project, I configured Google's SMTP server and used JavaMailSender to send verification emails containing UUID-based verification links to users.*/
@Service
public class UserNotificationService {
    JavaMailSender javaMailSender;
    TemplateEngine templateEngine;


    public UserNotificationService(JavaMailSender javaMailSender, TemplateEngine templateEngine){
        this.javaMailSender=javaMailSender;
        this.templateEngine=templateEngine;
    }



}
