package com.findwo.backend.user.exception;

import org.springframework.context.i18n.LocaleContextHolder;

import com.findwo.backend.shared.Messages;

public class InvalidTokenException extends RuntimeException{

    public InvalidTokenException(){
        super(Messages.getMessageForLocale("hoaxify.activate.user.invalid.token", LocaleContextHolder.getLocale()));
    }
    
}
