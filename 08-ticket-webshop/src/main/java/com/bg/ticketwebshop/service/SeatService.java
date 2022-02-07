package com.bg.ticketwebshop.service;

import java.util.List;

import com.bg.ticketwebshop.entity.Seat;

public interface SeatService {

	void save(Seat seat);
	
	void deleteById(Long seatId);
	
	List<Seat> findByEventId(Long eventId);
	
	List<Seat> findByUserId(Long userId);
	
	Seat findById(Long seatId);
	
}
