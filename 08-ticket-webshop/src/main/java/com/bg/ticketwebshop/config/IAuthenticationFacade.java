package com.bg.ticketwebshop.config;

import org.springframework.security.core.Authentication;

public interface IAuthenticationFacade {
	Authentication getAuthentication();
	public String loggedUserName();
	public String loggedUserDetails();
	
}
