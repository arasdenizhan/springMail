package com.example.springmail.controller;

import com.example.springmail.dto.MailDto;
import com.example.springmail.helper.MailHelper;
import com.example.springmail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/email")
public class EmailController {
    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/")
    public ResponseEntity<String> sendEmail(@RequestBody MailDto mailDto){
        try {
            MailHelper.validateMailDto(mailDto);
            emailService.sendEmail(mailDto);
            return ResponseEntity.ok("Email sent successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
