package com.bg.ticketwebshop.service;

import java.util.List;
import com.bg.ticketwebshop.entity.UsersEvents;

public interface UsersEventsService {

	List<UsersEvents> findByCustomerId(Long customerId);
	
	List<UsersEvents> findByProviderId(Long providerId);
	
}
