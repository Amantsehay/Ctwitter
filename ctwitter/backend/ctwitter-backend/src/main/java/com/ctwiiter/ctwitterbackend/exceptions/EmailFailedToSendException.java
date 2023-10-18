package com.ctwiiter.ctwitterbackend.exceptions;

public class EmailFailedToSendException extends RuntimeException{
    public EmailFailedToSendException (String emailHastBeenSent) {
        super(emailHastBeenSent);
    }
}
