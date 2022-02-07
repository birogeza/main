package com.bg.ticketwebshop.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.bg.ticketwebshop.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

	@Query(value="select * from event e where e.provider_id = ?1", 
			nativeQuery=true)
	List<Event> findByProviderId(Long providerId);
	
	//order by Event Start descending
	List<Event> findAllByOrderByEventStartDesc();
	
	
	
}
