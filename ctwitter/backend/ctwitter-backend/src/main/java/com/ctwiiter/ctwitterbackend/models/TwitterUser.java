package com.ctwiiter.ctwitterbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "twitter_user")
public class TwitterUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "twitter_user_id")
    private Integer userId;

    @Column(name = "first_name", nullable = false)
    private String firstName;
@Column(name = "last_name", nullable = false)
    private String lastName;

@Column(unique = true, name = "email_address")
    private String email;

@Column(name = "phone_number", nullable = false)
private String phone;
@Column (name = "dob")
private Date dataOfBirth;

@Column()
@JsonIgnore
private String password;
        @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
        @JoinTable(name = "user_role_junction",
                joinColumns = {@JoinColumn(name = "twitter_user_id")},
                inverseJoinColumns = {@JoinColumn (name = "role_id")})
        private Set<Role> authorities; // this mean the set of roles the user has

    @Column(name = "user_name", unique = true)
    private String userName;
    @Column(name = "verification_code", nullable = true)
    @JsonIgnore
    private Long verificationCode;

    @Column(name = "is_verified")
    private Boolean isVerified = false;



    public Date getDataOfBirth () {
        return dataOfBirth;
    }

    public Long getVerificationCode () {
        return verificationCode;
    }

    public void setVerificationCode (Long verificationCode) {
        this.verificationCode = verificationCode;
    }

    public Boolean getVerified () {
        return isVerified;
    }

    public void setVerified (Boolean verified) {
        isVerified = verified;
    }

    public TwitterUser(){
        this.authorities = new HashSet<>();
        isVerified = false; // when a new user is created the first time it will be not verified

    }

    public String getUserName(){
        return userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }


    public Integer getUserId () {
        return userId;
    }

    public void setUserId (Integer userId) {
        this.userId = userId;
    }

    public String getFirstName () {
        return firstName;
    }

    public void setFirstName (String firstName) {
        this.firstName = firstName;
    }

    public String getLastName () {
        return lastName;
    }

    public void setLastName (String lastName) {
        this.lastName = lastName;
    }

    public String getEmail () {
        return email;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public String getPhone () {
        return phone;
    }

    public void setPhone (String phone) {
        this.phone = phone;
    }

    public String getPassword () {
        return password;
    }

    public void setPassword (String password) {
        this.password = password;
    }

    public Date getDateOfBirth(){
        return dataOfBirth;
    }

    public void setDataOfBirth(Date dateOfBirth){
        this.dataOfBirth = dateOfBirth;
    }

    public Set<Role> getAuthorities () {
        return authorities;
    }

    public void setAuthorities (Set<Role> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String toString () {
        return "TwitterUser{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' ;

    }
}
