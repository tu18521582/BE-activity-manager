package com.kmstechnology.activitycrud.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class LoginException extends RuntimeException{
    public LoginException(String msg) {
        super(msg);
    }
}
