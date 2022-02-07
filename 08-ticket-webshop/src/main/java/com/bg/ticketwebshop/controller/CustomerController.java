package com.bg.ticketwebshop.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import com.bg.ticketwebshop.entity.Account;
import com.bg.ticketwebshop.entity.Event;
import com.bg.ticketwebshop.entity.Seat;
import com.bg.ticketwebshop.entity.UsersEvents;
import com.bg.ticketwebshop.service.AccountService;
import com.bg.ticketwebshop.service.DateTimeTransformator;
import com.bg.ticketwebshop.service.EventService;
import com.bg.ticketwebshop.service.SeatService;
import com.bg.ticketwebshop.service.UserService;
import com.bg.ticketwebshop.service.UsersEventsService;

@Controller
@RequestMapping("/customers")
public class CustomerController {	

	private UserService userService;
	private AccountService accountService;
	private EventService eventService;
	private SeatService seatService;
	private UsersEventsService ues;
	
	@Autowired
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}
	
	@Autowired
	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Autowired
	public void setSeatService(SeatService seatService) {
		this.seatService = seatService;
	}
	
	@Autowired
	public void setUsersEventsService(UsersEventsService ues) {
		this.ues = ues;
	}
	
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	/**
	 *	In the below Controller-method I send with the model "accounts" information.
	 *	Accounts details come from the accountService class, where I call the findByUserName()
	 *	method. In the findByUserName() method I put the userName, what I requested from the 
	 *	SpringSecurity. IMPORTANT that the userName have to be unique!
	 *
	 * 	If the logged user does not have registered account yet, she/he can register new.
	 * 	But 1 user can have only 1 account, so if the account is null for the user, than on
	 * 	the form I will show a link to register new account.
	 */
	
	@GetMapping("/showCustomerInfo")
	public String showCustomerInfo(Model model) {
		//request account for the user
		Long userId = userService.getLoggedUserId();
		
		List<UsersEvents> usersEvents = ues.findByCustomerId(userService.getLoggedUserId());
		
		model.addAttribute("accounts",accountService.findByUserId(userId));
		
		model.addAttribute("accountNr", accountService.accountAvailabilityChecker());
		
		model.addAttribute("userevents", usersEvents);
		System.out.println(">>> in ShowCustomerInfo: " + usersEvents);
		return "customer/customer-page";	
	}
	
	/**
	 * To show the Account registration form, have to create a new Account object.
	 * Also have to put to the form the current logged user's ID. (On the form this
	 * id will be hidden, also the account id will be hidden.)
	 * Have to send this new object and the userId with the Model to the form.
	 * 
	 * @param model
	 * @return
	 */
	
	@GetMapping("/showAccountRegForm")
	public String showAccountRegForm(Model model) {
		
		Account account = new Account();
		account.setUserId(userService.getLoggedUserId());
		model.addAttribute("account", account);
		System.out.println(">>>>ShowAccountReg: " + account);
		return "customer/account-registration-form";
	}
	
	@PostMapping("/saveAccount")
	public String saveAccount(
				@Valid @ModelAttribute("account") Account account, 
				BindingResult theBindingResult,
				Model model) {
		Long userId = userService.getLoggedUserId();		
		if (theBindingResult.hasErrors()){
			System.out.println(" >>>>>>>>>>>>>>>>>>>>>>> binding");
			
			model.addAttribute("account", account);
			model.addAttribute("userId", userId);
			return "customer/account-registration-form";
	     }else {
	    	 System.out.println(">>>>>" + account);
	    	 accountService.save(account);
	    	 return "redirect:/customers/showCustomerInfo";
	     }
	}
	
	
	
	@GetMapping("/showAccountUpdtForm")
	public String showAccountUpdtForm(@RequestParam("accountId") Long acctId, Model theModel) {

		Account theAccount = accountService.findById(acctId);

		theModel.addAttribute("account", theAccount);
		
		return "customer/account-registration-form";
	}
	
	@GetMapping("/deleteAcct")
	public String deleteAcct(@RequestParam("accountId") Long acctId) {
		//request the account info for the logged-user from the database
		accountService.deleteById(acctId);
		return "redirect:/customers/showCustomerInfo";
	}
	
	/**
	 * 
	 * show existing events. Where the event is 
	 * @param model
	 * @return
	 */
	
	@GetMapping("/showEvents")
	public String showEvents(Model model) {
		//set up the current DateTime to Long
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.now();
		String formattedDateTime = dateTime.format(formatter);
		Long nowLong = DateTimeTransformator.convertDateTimeToLong(formattedDateTime);
		
		List<Event> events = new ArrayList<>();
		events = eventService.findAll();
		model.addAttribute("events", events);
		model.addAttribute("nowLong", nowLong);
		return "customer/customer-events";
	}
	
	@GetMapping("/showTickets")
	public String showTickets(@RequestParam("eventId") Long eventId, Model model) {
		model.addAttribute("hasAccount", accountService.accountAvailabilityChecker());
		model.addAttribute("event", eventService.findById(eventId));		
		model.addAttribute("currentMoney", accountService.getCurrentMoney(userService.getLoggedUserId()));
		model.addAttribute("seats", seatService.findByEventId(eventId));
		
		return "customer/show-tickets";
	}
	/**
	 * get the seatId from the html site.
	 * Based on the seatId I can identify the seat-price for the event.
	 * I get the logged user money from the accountService, accountRepo based on the logged-user-name -> id.
	 * Based on the seatId I can identify the seatName and the event name.
	 * If the user has enough money to buy the ticket, she/he will be redirect to a successful buy site.
	 * In this case the ticket price will be deduct from user account and the chosen seat will mark as reserved.
	 * Have to save these changes to database.
	 * 
	 * If user doesn't have enough money she/he will redirect to a failed to buy site to inform about the failed
	 * transaction.
	 * 
	 * @param seatId
	 * @param model
	 * @return
	 */
	
	@GetMapping("/buyTickets")
	public String buyTickets(@RequestParam("seatId") Long seatId, Model model) {
		
		double userMoney = 0.0;
		Double seatPrice = (seatService.findById(seatId)).getPrice();
		//System.out.println(seatPrice);
		userMoney = accountService.getCurrentMoney(userService.getLoggedUserId());
		//System.out.println(userMoney);
		Seat theSeat = seatService.findById(seatId);
		//System.out.println(theSeat);
		Event theEvent = eventService.findById(theSeat.getEventId());
		//System.out.println(theEvent);
		
		if(userMoney >= seatPrice) {
			model.addAttribute("seat", theSeat.getSeatName());
			model.addAttribute("event", theEvent.getEventName());
			
			Account acct = accountService.findByUserId(userService.getLoggedUserId());
			//System.out.println(acct);
			acct.setAccountAmount(userMoney-seatPrice);
			model.addAttribute("remainingMoney", acct.getAccountAmount());
			//System.out.println(acct.getAccountAmount());
			accountService.save(acct);
			//System.out.println(acct);
			
			Seat seat = seatService.findById(seatId);
			//System.out.println(seat);
			seat.setReserved(true);
			seat.setUserId(userService.getLoggedUserId());
			seatService.save(seat);
			
			return "customer/successful-buy-ticket";
		}else {
			return "customer/failed-buy-ticket";
		}
	}	
}
