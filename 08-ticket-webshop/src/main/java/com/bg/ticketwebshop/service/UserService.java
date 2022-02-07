package com.bg.ticketwebshop.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.bg.ticketwebshop.entity.User;
import com.bg.ticketwebshop.reguser.RegUser;

public interface UserService extends UserDetailsService {

	User findByUserName(String username);

    void save(RegUser crmUser);	
    
    public Long getLoggedUserId();
    
    User findById(Integer userId);
    
    public User getLoggedUser();
}
