package com.luv2code.springboot.demo.mycoolapp.rest;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PropertySource(value={"application.properties"})
public class FunRestController {

	@Value("${team}")
	private String teamName;
	
	@Value("${coach}")
	private String coachName;
	
	@GetMapping("/")
	public String sayHello() {
		return "Hello World! A szerver idő: " + LocalDateTime.now() + "\n"
				+ "Hajrá: " + teamName +"\n"
						+ "Edző: " + coachName;
	}
	
}
