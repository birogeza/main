package com.bg.ticketwebshop.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade implements IAuthenticationFacade {

	@Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
	
	public String loggedUserName() {
		Authentication authentication = getAuthentication();
		return authentication.getName();
	}
	
	public String loggedUserDetails() {
		Authentication authentication = getAuthentication();
		return authentication.getDetails().toString();
	}

}
