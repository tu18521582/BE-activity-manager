package com.kmstechnology.activitycrud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Email or username already exist")
public class RegisterException extends RuntimeException{

}
