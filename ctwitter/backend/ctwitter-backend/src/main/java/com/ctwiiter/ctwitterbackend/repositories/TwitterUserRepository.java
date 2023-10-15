package com.ctwiiter.ctwitterbackend.repositories;

import com.ctwiiter.ctwitterbackend.models.TwitterUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TwitterUserRepository extends JpaRepository<TwitterUser, Integer> {


    public Optional<TwitterUser> findByUserName(String userName);
    // the use of optional class
}
