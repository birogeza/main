package com.bg.ticketwebshop.service;

import java.util.List;
import com.bg.ticketwebshop.entity.Event;

public interface EventService {

	List<Event> findAll();
	
	Event findById(Long id);
	
	void save(Event event);
	
	List<Event> findByProviderId(Long providerId);
	
	void deleteById(Long eventId);
	
}
