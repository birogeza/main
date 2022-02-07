package com.bg.ticketwebshop.service;

import com.bg.ticketwebshop.entity.Account;

public interface AccountService {

	void save(Account account);
	
	void deleteById(Long accountId);
	
	Account findByUserName(String userName);
	
	Account findById(Long accountId);
	
	Account findByUserId(Long userId);
	
	Double getCurrentMoney(Long userId);
	
	String accountAvailabilityChecker();
	
	void updateAccount(Double amount, Long accountId);
	
}
