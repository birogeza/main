package com.bg.ticketwebshop.controller;

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
import com.bg.ticketwebshop.entity.Event;
import com.bg.ticketwebshop.entity.Seat;
import com.bg.ticketwebshop.service.EventService;
import com.bg.ticketwebshop.service.ProvidersSalesService;
import com.bg.ticketwebshop.service.SeatService;
import com.bg.ticketwebshop.service.UserService;
import com.bg.ticketwebshop.service.UsersEventsService;

@Controller
@RequestMapping("/provider")
public class ProviderController {

	private UsersEventsService usersEventsService;
	private ProvidersSalesService prvdSlsSrvc;
	private UserService userService;
	private EventService eventService;
	private SeatService seatService;
	
	@Autowired
	public void setUsersEventsService(UsersEventsService usersEventsService) {
		this.usersEventsService = usersEventsService;
	}
	
	@Autowired
	public void setPrvdSlsSrvc(ProvidersSalesService prvdSlsSrvc) {
		this.prvdSlsSrvc = prvdSlsSrvc;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}

	@Autowired
	public void setSeatService(SeatService seatService) {
		this.seatService = seatService;
	}

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@GetMapping("/showProviders")
	public String showProviders(Model model) {
		model.addAttribute("events", eventService.findByProviderId(userService.getLoggedUserId()));
		model.addAttribute("userevents", usersEventsService.findByProviderId(userService.getLoggedUserId()));
		model.addAttribute("sales",prvdSlsSrvc.getProvidersSales(userService.getLoggedUserId()));
		return "provider/providers";
	}
	
	@GetMapping("/showEventForm")
	public String showEventForm(Model model) {
		Event theEvent = new Event();
		model.addAttribute("theEvent", theEvent);
		return "provider/event-form";
	}
	
	@PostMapping("/processEventForm")
	public String processEventForm(
			@Valid @ModelAttribute("theEvent") Event theEvent,
			BindingResult theBindingResult,
			Model model) {
		
		if (theBindingResult.hasErrors()){
			System.out.println(" >>>>>>>>> event binding");
			model.addAttribute("theEvent", theEvent);
			return "provider/event-form";
	     }else {
	    	eventService.save(theEvent);
	    	
	    	Seat theSeat = new Seat();

	    	theSeat.setEventId(theEvent.getId());
	    	model.addAttribute("eventId", theEvent.getId());
	    	System.out.println(">>> EVENT_ID: " + theEvent.getId());
	    	System.out.println(theSeat);
	    	model.addAttribute("newSeat", theSeat);
	    	
	 	    return "provider/seat-form";
	     }     
	}
	
	@GetMapping("/showSeatForm")
	public String showSeatForm(
			@RequestParam("eventId") Long eventId, 
			Model theModel) {
		
		Seat newSeat = new Seat();
		newSeat.setEventId(eventId);
		theModel.addAttribute("newSeat",newSeat);
		theModel.addAttribute("eventId", eventId);
		System.out.println(eventId);
		return "provider/seat-form";
	}
	
	
	@PostMapping("/processSeatForm")
	public String processSeatForm(
			@Valid @ModelAttribute("newSeat") Seat newSeat,
			BindingResult theBindingResult,
			Model theModel
			){
		
		if(theBindingResult.hasErrors()) {
			theModel.addAttribute("newSeat", newSeat);
			System.out.println(" >>>>>>>>> seat binding");
			return "provider/seat-form";
		}else {
			newSeat.setReserved(false);
			System.out.println(newSeat);
			seatService.save(newSeat);
			return "redirect:/provider/showProviders";
		}
	}
	
	@GetMapping("/showEventsSeats")
	public String showEventsSeats(@RequestParam("eventId") Long eventId, Model model) {
		model.addAttribute("seats", seatService.findByEventId(eventId));
		model.addAttribute("eventId", eventId);
		model.addAttribute("eventName", (eventService.findById(eventId)).getEventName());
		
		System.out.println(">>>> seats" + seatService.findByEventId(eventId));
		System.out.println(">>>> eventId: " + eventId);
		System.out.println(">>>> eventName: " + (eventService.findById(eventId)).getEventName());
		
		return "provider/seat-list";
	}
	
	
	@GetMapping("/updateSeat")
	public String updateSeat(@RequestParam Long seatId, Model model) {
		model.addAttribute("newSeat", seatService.findById(seatId));
		System.out.println(seatService.findById(seatId));
		model.addAttribute("eventId", seatService.findById(seatId).getEventId());
		return "provider/seat-form";
	}
	
	@GetMapping("/deleteSeat")
	public String deleteSeat(@RequestParam Long seatId) {
		seatService.deleteById(seatId);
		return "redirect:/provider/showProviders";
	}
	
	
	//Events
		//add new event
		//list event for the provider(user with provider role)
		//update event details
		//delete event
		//check events details (seats)
	
	//Seats
		//add new seat for
		//list available seats
		//list all seats
		//update seat details
		//delete seat
	
	//list customer list for event, export it to csv file
	

}
