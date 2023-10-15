package com.ctwiiter.ctwitterbackend.models;


import java.sql.Date;

public class RegistrationObject  {
    private String firstName;
    private String lastName;

    private String email;
    private String phone;
// the user name will authomatically generated for us
    private Date dateOfBirth;
    private String password;

    public RegistrationObject(){
        // the no args
    }

    public RegistrationObject (
                                String firstName,
                                String lastName,
                                String email,
                                String phone,
                                Date dateOfBirth,
                                String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
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


    public Date getDateOfBirth () {
        return dateOfBirth;
    }

    public void setDateOfBirth (Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPassword () {
        return password;
    }

    public void setPassword (String password) {
        this.password = password;
    }
}
