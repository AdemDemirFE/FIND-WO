package com.findwo.backend.user.exception;

import org.springframework.context.i18n.LocaleContextHolder;

import com.findwo.backend.shared.Messages;

public class ActivationNotificationException  extends RuntimeException {
    
    public ActivationNotificationException() {
        super(Messages.getMessageForLocale("findwo.create.user.email.failure", LocaleContextHolder.getLocale()));
    }
}
