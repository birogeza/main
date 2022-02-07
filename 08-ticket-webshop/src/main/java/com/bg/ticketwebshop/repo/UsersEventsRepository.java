package com.bg.ticketwebshop.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bg.ticketwebshop.entity.UsersEvents;

public interface UsersEventsRepository extends JpaRepository<UsersEvents, Long> {

	@Query(value="select * from users_events where reserved=true and customer_id = ?1", 
		   nativeQuery=true)
	List<UsersEvents> findByCustomerId(Long customerId);
	
	@Query(
			value="select * from users_events where reserved=true and provider_id = ?1", 
			nativeQuery=true)
	List<UsersEvents> findByProviderId(Long providerId);
	
}
