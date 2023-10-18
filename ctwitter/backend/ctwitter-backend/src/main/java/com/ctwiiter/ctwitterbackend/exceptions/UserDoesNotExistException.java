package com.ctwiiter.ctwitterbackend.exceptions;

public class UserDoesNotExistException extends Exception {

    public UserDoesNotExistException(){
        super("The user doesnt exist yet");
    }
}
