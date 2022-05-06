package com.example.springmail.service.impl;

import com.example.springmail.dto.MailDto;
import com.example.springmail.exception.MailSentException;
import com.example.springmail.helper.MailHelper;
import com.example.springmail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void sendEmail(MailDto mailDto) throws MailSentException {
        try{
            emailSender.send(MailHelper.prepareMail(mailDto));
        } catch (MailException e){
            throw new MailSentException(e.getMessage());
        }
    }
}
