package com.lotterygame.service;

import com.lotterygame.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class UserDetailsImplementation implements UserDetails {

    private User user;

    @Autowired
    public UserDetailsImplementation (User user){
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("USER_ROLE"));

        return authorities; //nincsenek authorityk
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        System.out.println("username: ");
        System.out.println(user.getUserName());
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; //ezzel most nem foglalkozok, defaultban ne legyen lejárt
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; //ezzel sem foglalkozok, defaultban ne legyen lockolt account
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; //defaultban legyen mindig ture, sosem jár le
    }

    @Override
    public boolean isEnabled() {
        return true; //alapértelmezetten mindig engedélyezett a fiók
    }




}
