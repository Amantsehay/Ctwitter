package com.ctwiiter.ctwitterbackend.services;

import com.ctwiiter.ctwitterbackend.exceptions.EmailAlreadyTakenException;
import com.ctwiiter.ctwitterbackend.exceptions.EmailFailedToSendException;
import com.ctwiiter.ctwitterbackend.exceptions.IncorrectVerificationCodeException;
import com.ctwiiter.ctwitterbackend.exceptions.UserDoesNotExistException;
import com.ctwiiter.ctwitterbackend.models.RegistrationObject;
import com.ctwiiter.ctwitterbackend.models.Role;
import com.ctwiiter.ctwitterbackend.models.TwitterUser;
import com.ctwiiter.ctwitterbackend.repositories.RoleRepository;
import com.ctwiiter.ctwitterbackend.repositories.TwitterUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class TwitterUserService {

    private final TwitterUserRepository twitterUserRepository;
    private final RoleRepository roleRepository;
    private final MailService mailService; // the mail service will be used to send the verification code
    private final PasswordEncoder passwordEncoder; // the password encoder will use

@Autowired
    public TwitterUserService(TwitterUserRepository twitterUserRepository, RoleRepository roleRepository,
                              MailService mailService,
                              PasswordEncoder passwordEncoder){
        this.twitterUserRepository = twitterUserRepository;
        this.roleRepository = roleRepository;
        this.mailService = mailService;
        this.passwordEncoder = passwordEncoder;
    }


    public TwitterUser registerUser(RegistrationObject registrationObject){
//    roleRepository.save(new Role(1, "USER"));
    TwitterUser twitterUser = new TwitterUser();
    twitterUser.setFirstName(registrationObject.getFirstName());
    twitterUser.setLastName(registrationObject.getLastName());
//    twitterUser.setUserName(registrationObject.getUserName());
    twitterUser.setEmail(registrationObject.getEmail());
    twitterUser.setPassword(registrationObject.getPassword());
    twitterUser.setPhone(registrationObject.getPhone());
    twitterUser.setDataOfBirth(registrationObject.getDateOfBirth());

    String name = twitterUser.getFirstName() + twitterUser.getLastName();
    String tempName = "";
    // the proble is an infinite loop here

    boolean nameTaken= true;
    while (nameTaken){
        tempName = generateUserName(name);
        if (twitterUserRepository.findByUserName(tempName).isEmpty()){ // the username is the tempName here
            nameTaken = false;

        }

        twitterUser.setUserName(tempName);

    }

    Set<Role> roles  = twitterUser.getAuthorities();
    roles.add(roleRepository.findByAuthority("User").orElse(null));
    twitterUser.setAuthorities(roles);

    try{
        return twitterUserRepository.save(twitterUser);
    }
    catch (Exception e){
        throw new EmailAlreadyTakenException();
    }


    }

    private String generateUserName(String name){
        long generatedNumber = (long) (Math.floor(Math.random()*(1000_000_000)));
        return name + generatedNumber;



    }

    public TwitterUser getByUserName (String userName) {
    return twitterUserRepository.findByUserName(userName).orElseThrow();
    }

    public TwitterUser updateUser (TwitterUser twitterUser) {
          return twitterUserRepository.save(twitterUser);


    }

    public void generateEmailVerificationCode (String userName) throws EmailFailedToSendException {
        try {
            TwitterUser twitterUser = twitterUserRepository.findByUserName(userName).orElseThrow(UserDoesNotExistException::new);
            twitterUser.setVerificationCode(generateVerificationCode());
            mailService.sendEmail(twitterUser.getEmail(), "Verification Code", "Your verification code is " + twitterUser.getVerificationCode());
            twitterUserRepository.save(twitterUser); // the email will be sent to the current email address from the
            // user object and the verification code will be saved to the database
        } catch (UserDoesNotExistException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
                    throw new EmailFailedToSendException("Email has not been sent");

        }

    }

    private Long generateVerificationCode () {
            return (long) Math.floor(Math.random()*100_000_000);
    }

    public TwitterUser verifyEmail (String userName, Long code) {

        try {
            TwitterUser twitterUser     =
                    twitterUserRepository.findByUserName(userName).orElseThrow(UserDoesNotExistException::new);

            if (code.equals(twitterUser.getVerificationCode())){
                twitterUser.setVerified(true);
                twitterUser.setVerificationCode(null); // the code is no longer needed after verification
                return twitterUserRepository.save(twitterUser);
            }
            else {
                throw new IncorrectVerificationCodeException();
            }
        } catch (UserDoesNotExistException | IncorrectVerificationCodeException e) {
            e.printStackTrace();
        }
        return null;

    }


    public TwitterUser setPassword (String userName, String password) {

        TwitterUser twitterUser;
        try {
            twitterUser = twitterUserRepository.findByUserName(userName).orElseThrow(UserDoesNotExistException::new);
        } catch (UserDoesNotExistException e) {
            throw new RuntimeException(e);
        }
        String encodedPassword = passwordEncoder.encode(password);
            twitterUser.setPassword(encodedPassword);

            return twitterUserRepository.save(twitterUser);

    }
}
