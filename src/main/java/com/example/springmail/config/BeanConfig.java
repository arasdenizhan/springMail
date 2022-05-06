package com.example.springmail.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class BeanConfig {

    @Value("${spring.mail.host}")
    private String hostAddress;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")

    private String password;

    @Value("${spring.mail.port}")
    private int port;

    @Value("${spring.mail.properties.mail.smtp.auth}")
    private String hasAuthentication;

    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private String hasTSL;

    @Value("${spring.mail.properties.mail.transport.protocol}")
    private String transportProtocol;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(hostAddress);
        mailSender.setPort(port);
        mailSender.setProtocol(transportProtocol);
        mailSender.setUsername(username);
        mailSender.setPassword(password);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", hasAuthentication);
        props.put("mail.smtp.starttls.enable", hasTSL);

        return mailSender;
    }
}
