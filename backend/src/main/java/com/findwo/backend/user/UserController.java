package com.findwo.backend.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.findwo.backend.error.ApiError;
import com.findwo.backend.shared.GenericMessage;
import com.findwo.backend.shared.Messages;
import com.findwo.backend.user.dto.UserCreate;
import com.findwo.backend.user.exception.ActivationNotificationException;
import com.findwo.backend.user.exception.NotUniqueEmailExeption;

import jakarta.validation.Valid;

import java.util.stream.Collectors;
@RestController
public class UserController {

    @Autowired 
    UserService userService;

    @Autowired
    MessageSource messageSource;

    @PostMapping("/api/v1/users")
    GenericMessage createUser(@Valid @RequestBody UserCreate user){
        userService.save(user.toUser());
        String message = Messages.getMessageForLocale("findwo.create.user.success.message",  LocaleContextHolder.getLocale());
        return new GenericMessage(message);
    }

    @PatchMapping("/api/v1/users/{token}/active")
    GenericMessage activateUser(@PathVariable String token){
        userService.activateUser(token);
        String message = Messages.getMessageForLocale("hoaxify.activate.user.success.message", LocaleContextHolder.getLocale());
        return new GenericMessage(message);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ApiError> handleMethodNotValidEx(MethodArgumentNotValidException exception) {
        ApiError apiError = new ApiError();
        apiError.setPath("/api/v1/users");
        String message = Messages.getMessageForLocale("findwo.error.validation", LocaleContextHolder.getLocale());
        apiError.setMessage(message);
        apiError.setStatus(400);
        var validationErrors = exception.getBindingResult().getFieldErrors().stream().collect(Collectors.toMap
        (FieldError::getField, FieldError::getDefaultMessage, (existing, replacing) -> existing));
        apiError.setValidationErrors(validationErrors);
        return ResponseEntity.status(400).body(apiError);
    }
    
    @ExceptionHandler(NotUniqueEmailExeption.class)
    ResponseEntity<ApiError> handleNotUniqEmailEx(NotUniqueEmailExeption exception) {
        ApiError apiError = new ApiError();
        apiError.setPath("/api/v1/users");
        apiError.setMessage(exception.getMessage());
        apiError.setStatus(400);
        apiError.setValidationErrors(exception.getValidationErrors());
        return ResponseEntity.status(400).body(apiError);
    }

    @ExceptionHandler(ActivationNotificationException.class)
    ResponseEntity<ApiError> handleActivationNotificationException(ActivationNotificationException exception) {
        ApiError apiError = new ApiError();
        apiError.setPath("/api/v1/users");
        apiError.setMessage(exception.getMessage());
        apiError.setStatus(502);
        return ResponseEntity.status(502).body(apiError);
    }

}
