package com.demo.parrilla.service;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class ErrorMessageService {

    @Autowired
    MessageSource messageResource;

    public String getMessage(String code) {
        return messageResource.getMessage(code, null, new Locale("es", "AR"));
    }
}