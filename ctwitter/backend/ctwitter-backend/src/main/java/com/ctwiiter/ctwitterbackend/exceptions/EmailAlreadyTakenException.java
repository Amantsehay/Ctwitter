package com.ctwiiter.ctwitterbackend.exceptions;

import java.io.Serial;

public class EmailAlreadyTakenException  extends RuntimeException{

    private static final long serialVersionUID = 1L;
    public EmailAlreadyTakenException(){
        super("email provided is already taken");
    }
}
