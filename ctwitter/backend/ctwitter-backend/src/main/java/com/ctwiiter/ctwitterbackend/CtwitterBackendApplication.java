package com.ctwiiter.ctwitterbackend;

import com.ctwiiter.ctwitterbackend.models.Role;
import com.ctwiiter.ctwitterbackend.models.TwitterUser;
import com.ctwiiter.ctwitterbackend.repositories.RoleRepository;
import com.ctwiiter.ctwitterbackend.repositories.TwitterUserRepository;
import com.ctwiiter.ctwitterbackend.services.TwitterUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class CtwitterBackendApplication {

    public static void main (String[] args) {
        SpringApplication.run(CtwitterBackendApplication.class, args);
    }
@Bean
    CommandLineRunner run (RoleRepository roleRepository, TwitterUserService twitterUserService){
    return args -> {
//        roleRepository.save(new Role(2, "USER")); // save  a new role to the repo
//        TwitterUser twitterUser = new TwitterUser();
//        twitterUser.setFirstName("Second Ninjasdfsdjf;lk");
//        twitterUser.setLastName("Warrior ll");
//        twitterUser.setPassword("passowrdforseconduser");
//        twitterUser.setEmail("secondusers@gmail.com");
//        twitterUser.setPhone("09322233323");
//        twitterUser.setUserName("userNameForSecondUser");
//        twitterUserService.registerUser(twitterUser);




    };
}
}
