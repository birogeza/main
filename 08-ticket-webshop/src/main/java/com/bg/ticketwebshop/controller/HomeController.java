package com.bg.ticketwebshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.bg.ticketwebshop.config.IAuthenticationFacade;

@Controller
public class HomeController {
	
	@Autowired
    private IAuthenticationFacade authenticationFacade;
	
	@GetMapping("/")
	public String showIndex() {
		authenticationFacade.loggedUserName();
		return "home";
	}
}
