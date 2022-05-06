package com.example.springmail.exception;

public class MailDtoNotValidException extends MailAppException{
    public MailDtoNotValidException(String message) {
        super(message);
    }
}
