package com.example.springmail.helper;

import com.example.springmail.dto.MailDto;
import com.example.springmail.exception.MailDtoNotValidException;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.mail.SimpleMailMessage;

import java.lang.reflect.Field;
import java.util.stream.Stream;

public final class MailHelper {
    private MailHelper(){
        throw new UnsupportedOperationException("You cannot create Mail Helper instance.");
    }

    public static SimpleMailMessage prepareMail(MailDto mailDto){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailDto.getSender());
        message.setTo(mailDto.getReceiver());
        message.setSubject(mailDto.getSubject());
        message.setText(mailDto.getText());
        return message;
    }

    public static void validateMailDto(MailDto mailDto) throws MailDtoNotValidException {
        Field[] declaredFields = MailDto.class.getDeclaredFields();
        boolean allMatch = Stream.of(declaredFields).allMatch(field -> {
            try {
                field.setAccessible(true);
                boolean isEmpty = StringUtils.isNotEmpty((String) field.get(mailDto));
                field.setAccessible(false);
                return isEmpty;
            } catch (IllegalAccessException e) {
                throw new UnsupportedOperationException(e.getMessage());
            }
            finally {
                field.setAccessible(false);
            }
        });
        if(BooleanUtils.isFalse(allMatch)){
            throw new MailDtoNotValidException("There are empty Fields.");
        }
    }
}
