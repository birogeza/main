package com.lotterygame.service;

import com.lotterygame.entity.User;
import com.lotterygame.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImplementation implements UserService, UserDetailsService {

    private UserRepository userRepository;

    private String activeUserName;
    private Long activeUserId;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository){
        this.userRepository = userRepository;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        System.out.println("User keresés az adatbázisban");
        User user = findByUserName(username);
        if(user==null){
            throw new UsernameNotFoundException(username);
        }
        return new UserDetailsImplementation(user);
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public String registerUser(User userToRegister){
        User userCheck = userRepository.findByUserName(userToRegister.getUserName());
        if(userCheck != null){
            return "Már létezik ez a username! Már regisztráltak ezzel a névvel, kérlek válassz másik nevet!";
        }
        userRepository.save(userToRegister);

        return "ok";
    }

    public String getActiveUserName(){
        if(findByUserName(activeUserName) != null) {
            return findByUserName(activeUserName).getFirstName() + " " + findByUserName(activeUserName).getLastName();
        }else{
            return "Nincs ilyen felhasználó!";
        }
    }

    public Long getActiveUserId(){
        return findByUserName(activeUserName).getId();
    }


    public void setActiveUserNameID(User user){
        this.activeUserName = user.getUserName();
        this.activeUserId = user.getId();
        System.out.println("Az aktív user neve: " + activeUserName);
    }

    @Override
    public ArrayList<User> listAllUsers(){
        ArrayList<User> users = userRepository.findAll();
        return users;
    }



}
