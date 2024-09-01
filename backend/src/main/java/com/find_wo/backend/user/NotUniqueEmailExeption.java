package com.find_wo.backend.user;

import java.util.Collections;
import java.util.Map;

import org.springframework.context.i18n.LocaleContextHolder;

import com.find_wo.backend.shared.Messages;

public class NotUniqueEmailExeption extends RuntimeException {

    public NotUniqueEmailExeption() {
        super(Messages.getMessageForLocale("find_wo.error.validation", LocaleContextHolder.getLocale()));
    }
    
    public Map<String, String> getValidationErrors() {
        return Collections.singletonMap("email", Messages.getMessageForLocale("find_wo.constraint.email.notunique", LocaleContextHolder.getLocale()));
    }
}
