package com.lotterygame.service;

import com.lotterygame.entity.User;

import java.util.ArrayList;

public interface UserService {
    //a service rétegnek kell elérnie az adatbázisból egy User-t adott username alapján
    public User findByUserName(String userName);

    public String registerUser(User user);

    public ArrayList<User> listAllUsers();



}
