package com.kmstechnology.activitycrud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class RegisterException extends RuntimeException{
    public RegisterException(String msg) {
        super(msg);
    }
}
