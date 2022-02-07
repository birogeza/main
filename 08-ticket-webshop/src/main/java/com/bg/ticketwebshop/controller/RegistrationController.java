package com.bg.ticketwebshop.controller;

import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.bg.ticketwebshop.entity.User;
import com.bg.ticketwebshop.reguser.RegUser;
import com.bg.ticketwebshop.service.UserService;


@Controller
@RequestMapping("/register")
public class RegistrationController {

    
    
    
	/**
	 * @author birog
	 * set up the Logger to be able to log the registration mechanism
	 */
	private Logger logger = Logger.getLogger(getClass().getName());
	
	/**
	 * @author birog 
	 * Inject the userService with a setter injection
	 */
	private UserService userService;
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	/**
	 * @author birog
	 * @param dataBinder
	 * 
	 * this method is for Trim() the fields
	 */
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	/**
	 * 
	 * @param theModel
	 * @author birog
	 * @return
	 * 
	 * this method will show the Registration-Form and we send with the Model the regUser
	 * as a new instance of RegUser class.
	 * On the Form we will fill out this instance with values, and we will send it back
	 * to the "/processRegistrationForm"
	 * 
	 */
	
	@GetMapping("/showRegistrationForm")
	public String showMyLoginPage(Model theModel) {
		
		theModel.addAttribute("regUser", new RegUser());
		
		return "login/registration-form";
	}
	
	/**
	 * @author birog
	 * @param theCrmUser
	 * @param theBindingResult
	 * @param theModel
	 * @return
	 * 
	 * We get the filled out "regUser" object from the form
	 * 
	 */
	
	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(
				@Valid @ModelAttribute("regUser") RegUser regUser, 
				BindingResult theBindingResult, 
				Model theModel) {
		
		String username = regUser.getUsername();
		logger.info("Processing registration form for: " + username);
		
		// form validation, checks if the necessary fields were filled out
		 if (theBindingResult.hasErrors()){
			 return "login/registration-form";
	     }

		/* 
		 * check the database if user already exists, if exists send back to
		 * the form with error message, that the 'username' already exist. 
		 */
        User existing = userService.findByUserName(username);
        if (existing != null){
        	theModel.addAttribute("regUser", new RegUser());
			theModel.addAttribute("registrationError", "User name already exists.");

			logger.warning("User name already exists.");
        	return "login/registration-form";
        }
     // create user account        						
        userService.save(regUser);
        
        logger.info("Successfully created user: " + username);
        logger.info(regUser.toString());
        
        return "login/registration-confirmation";		
	}
}
