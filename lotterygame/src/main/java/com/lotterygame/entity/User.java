package com.lotterygame.entity;


import javax.persistence.*;

import com.lotterygame.entity.Game;

import java.util.ArrayList;
import java.util.List;

@Entity
//@Table( name="users" )
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column( unique=true, nullable=false )
    private String userName;
    @Column( nullable=false )
    private String password;
    private String firstName;
    private String lastName;
    private String gender;
    private String vehicle;
    private String email;
    private String phone;


    @OneToMany(mappedBy = "user")
    private List<Game> games;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public User(String userName, String password, String firstName, String lastName, String gender, String vehicle, String email, String phone) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.vehicle = vehicle;
        this.email = email;
        this.phone = phone;
    }

    public User(){}


}

