package com.bg.ticketwebshop.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bg.ticketwebshop.entity.Seat;

public interface SeatRepository extends JpaRepository<Seat, Long> {
	
	@Query(value="select * from seat s where s.user_id=?1 order by event_id", 
			nativeQuery=true)
	List<Seat> findByUserId(Long userId);
	
	@Query(value="select * from seat s where event_id=?1", nativeQuery=true)
	List<Seat> findByEventId(Long userId);
	
}
