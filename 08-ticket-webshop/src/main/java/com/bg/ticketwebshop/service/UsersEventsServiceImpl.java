package com.bg.ticketwebshop.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bg.ticketwebshop.entity.UsersEvents;
import com.bg.ticketwebshop.repo.UsersEventsRepository;

@Service
public class UsersEventsServiceImpl implements UsersEventsService {

	private UsersEventsRepository usersEventsRepository;
	
	@Autowired
	public void setUsersEventsRepository(UsersEventsRepository usersEventsRepository) {
		this.usersEventsRepository = usersEventsRepository;
	}
	
	@Override
	@Transactional
	public List<UsersEvents> findByCustomerId(Long customerId) {
		
		List<UsersEvents> ue = usersEventsRepository.findByCustomerId(customerId);
		for(UsersEvents tmpUe : ue) {
			System.out.println(">>>>" + tmpUe);
		}
		
		return usersEventsRepository.findByCustomerId(customerId);
		
	}

	@Override
	@Transactional
	public List<UsersEvents> findByProviderId(Long provider_id) {
		return usersEventsRepository.findByProviderId(provider_id);
	}

}
