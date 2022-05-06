package com.example.springmail.exception;

public class MailSentException extends MailAppException{
    public MailSentException(String message) {
        super(message);
    }
}
