package com.ctwiiter.ctwitterbackend.controllers;

import com.ctwiiter.ctwitterbackend.exceptions.EmailAlreadyTakenException;
import com.ctwiiter.ctwitterbackend.exceptions.EmailFailedToSendException;
import com.ctwiiter.ctwitterbackend.exceptions.IncorrectVerificationCodeException;
import com.ctwiiter.ctwitterbackend.exceptions.UserDoesNotExistException;
import com.ctwiiter.ctwitterbackend.models.RegistrationObject;
import com.ctwiiter.ctwitterbackend.models.TwitterUser;
import com.ctwiiter.ctwitterbackend.repositories.TwitterUserRepository;
import com.ctwiiter.ctwitterbackend.services.TwitterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final TwitterUserService twitterUserService;

    @Autowired
    public AuthenticationController(TwitterUserService twitterUserService){
        this.twitterUserService = twitterUserService;
    }
// the authentication controll is where the real stuff happens
    // the registerUser method is where the user is registered
    @ExceptionHandler({EmailAlreadyTakenException.class})
    public ResponseEntity<String> handleEmailTaken(){
        return new ResponseEntity<>("the email is already taken", HttpStatus.CONFLICT);
    }

    @PostMapping("/register")
    public TwitterUser registerUser(@RequestBody RegistrationObject registrationObject){
        return twitterUserService.registerUser(registrationObject);
    }

    @ExceptionHandler({UserDoesNotExistException.class})
    public ResponseEntity<String> handleUserDoesNotExist(){
        return  new ResponseEntity<>("The user doesn exist ", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({EmailFailedToSendException.class})
        public ResponseEntity<String> handleEmailFailedToSend(){
            return new ResponseEntity<>("The email failed to send", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler({IncorrectVerificationCodeException.class})
    public ResponseEntity<String> handleIncorrectVerificationCode(){
        return new ResponseEntity<>("The code provided doesn much the user's code", HttpStatus.CONFLICT);
    }
    @PutMapping("/update/phone")
    public TwitterUser updatePhoneNumber(@RequestBody LinkedHashMap<String, String> body){

    String userName = body.get("userName");
    String phone = body.get("phone"); // the hashmap contains the key value pair of username and phone

    TwitterUser twitterUser = twitterUserService.getByUserName(userName);
    twitterUser.setPhone(phone);
    return twitterUserService.updateUser(twitterUser);

    }

    @PostMapping("/email/code")
    public ResponseEntity<String>  createEmailVerification(@RequestBody LinkedHashMap<String, String> body){
        String userName = body.get("userName");
        try {
            twitterUserService.generateEmailVerificationCode(userName);
        } catch (EmailFailedToSendException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>("The verification code has been sent to the email ",
                HttpStatus.OK );
    }

    @PostMapping("/email/verify")
    public TwitterUser verifyEmail(@RequestBody LinkedHashMap<String, String> body){
        String userName = body.get("userName");
        Long code = Long.parseLong(body.get("verificationCode"));
        return twitterUserService.verifyEmail(userName, code);
    }

    @PutMapping("/password/update")
    public TwitterUser updatePassword(@RequestBody LinkedHashMap<String, String> body){
        String userName = body.get("userName");
        String password = body.get("password");

        return twitterUserService.setPassword(userName, password);
    }
}
