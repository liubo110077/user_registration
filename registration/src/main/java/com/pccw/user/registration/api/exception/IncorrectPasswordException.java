package com.pccw.user.registration.api.exception;

public class IncorrectPasswordException extends RuntimeException{

    public IncorrectPasswordException(String message) {
        super(message);
    }
}
