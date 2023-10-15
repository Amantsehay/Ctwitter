package com.ctwiiter.ctwitterbackend.exceptions;

public class IncorrectVerificationCodeException extends Exception {

    public IncorrectVerificationCodeException(){
        super("Thse code you have passed is incorrect");
    }
}
