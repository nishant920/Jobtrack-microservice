package com.jobtrack.notification_api.service;

import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


/*
* avaMailSender is Spring Boot's mail abstraction built on top of the Java Mail API.
* It allows an application to send emails through an SMTP server.
*  In my project, I configured Google's SMTP server and used JavaMailSender to send verification emails containing UUID-based verification links to users.*/
@Service
@Slf4j
public class UserNotificationService {
    @Autowired
    JavaMailSender javaMailSender;
    TemplateEngine templateEngine;


    public UserNotificationService(JavaMailSender javaMailSender, TemplateEngine templateEngine){
        this.javaMailSender=javaMailSender;
        this.templateEngine=templateEngine;
    }
    @Value("${app.base.url}")
    String appbaseUrl;
    public void userEmailVerifyNotification(String email, String token){
        Context context = new Context();
        String verificationLink = appbaseUrl + "/api/v1/user/verify?token=" + token;
        context.setVariable("Verification-link", verificationLink);

        String htmlContent = templateEngine.process("User-verification-mail", context);

    try{
        MimeMessage mimeMessage =javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper =new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("Email Verification");
        mimeMessageHelper.setText(htmlContent, true);
        javaMailSender.send(mimeMessage);

    }catch (Exception e) {
        log.error("Failed to send verification link", e);
        throw new RuntimeException("Failed to send verification link");
        }
    }

    }