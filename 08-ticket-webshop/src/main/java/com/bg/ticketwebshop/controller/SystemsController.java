package com.bg.ticketwebshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/systems")
public class SystemsController {

	@GetMapping("/showSystemInfo")
	public String showSystemInfo() {
		return "system/system-page";
	}
	
}
