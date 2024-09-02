package com.findwo.backend.user.exception;

import java.util.Collections;
import java.util.Map;

import org.springframework.context.i18n.LocaleContextHolder;

import com.findwo.backend.shared.Messages;

public class NotUniqueEmailExeption extends RuntimeException {

    public NotUniqueEmailExeption() {
        super(Messages.getMessageForLocale("findwo.error.validation", LocaleContextHolder.getLocale()));
    }
    
    public Map<String, String> getValidationErrors() {
        return Collections.singletonMap("email", Messages.getMessageForLocale("findwo.constraint.email.notunique", LocaleContextHolder.getLocale()));
    }
}
