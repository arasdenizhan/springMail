package com.example.springmail.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MailDto {
    private String sender;
    private String receiver;
    private String subject;
    private String text;
}
