package com.example.springmail.service;

import com.example.springmail.dto.MailDto;
import com.example.springmail.exception.MailSentException;

public interface EmailService {
    void sendEmail(MailDto mailDto) throws MailSentException;
}
