package com.bg.ticketwebshop.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bg.ticketwebshop.entity.Seat;
import com.bg.ticketwebshop.repo.SeatRepository;

@Service
public class SeatServiceImpl implements SeatService {

	private SeatRepository seatRepo;
	
	@Autowired
	public SeatServiceImpl(SeatRepository seatRepo) {
		this.seatRepo = seatRepo;
	}
	
	
	@Override
	@Transactional
	public void save(Seat seat) {
		seatRepo.save(seat);
	}

	@Override
	@Transactional
	public void deleteById(Long seatId) {
		seatRepo.deleteById(seatId);
	}

	@Override
	@Transactional
	public List<Seat> findByEventId(Long eventId) {
		return seatRepo.findByEventId(eventId);
	}

	@Override
	@Transactional
	public List<Seat> findByUserId(Long userId) {
		return seatRepo.findByUserId(userId);
	}

	@Override
	@Transactional
	public Seat findById(Long seatId) {
		Optional<Seat> result = seatRepo.findById(seatId);
		Seat theSeat = null;
		
		if(result.isPresent()) {
			theSeat = result.get();
		}else {
			throw new RuntimeException("There is no seat for this seatId: " + seatId);
		}
		return theSeat;
	}

}
