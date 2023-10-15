package com.ctwiiter.ctwitterbackend.exceptions;

public class EmailFailedToSendException extends Exception {
    public EmailFailedToSendException (String emailHastBeenSent) {
        super(emailHastBeenSent);
    }
}
