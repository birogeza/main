package com.bg.ticketwebshop.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bg.ticketwebshop.entity.Event;
import com.bg.ticketwebshop.repo.EventRepository;

@Service
public class EventServiceImpl implements EventService {

	private EventRepository eventRepo;
	private UserService userService;
	// SETTER Injections

	@Autowired
	public void EventRepository(EventRepository eventRepo) {
		this.eventRepo = eventRepo;
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}



	@Override
	@Transactional
	public List<Event> findAll() {
		return eventRepo.findAllByOrderByEventStartDesc();
	}

	@Override
	@Transactional
	public Event findById(Long id) {
		
		Optional<Event> result = eventRepo.findById(id); 
		
		Event theEvent = null;
		
		if(result.isPresent()) {
			theEvent = result.get();
		}else {
			throw new RuntimeException("There is no event for this eventId: " + id);
		}
		return theEvent;
	}

	@Override
	@Transactional
	public void save(Event event) {
		/*itt megkapjuk az event-et, de a szöveg dátumot számmá kell alakítani
		 * és el kell menteni a provider_id-t is, ha még nincs mentve.*/
		
		event.setEventStart(DateTimeTransformator.convertDateTimeToLong(event.getEventStartString()));
		event.setEventEnd(DateTimeTransformator.convertDateTimeToLong(event.getEventEndString()));
		event.setProviderId(userService.getLoggedUserId());
		eventRepo.save(event);

	}

	@Override
	@Transactional
	public List<Event> findByProviderId(Long providerId) {
		return eventRepo.findByProviderId(providerId);
	}

	@Override
	@Transactional
	public void deleteById(Long eventId) {
		eventRepo.deleteById(eventId);
	}
	
	
}
