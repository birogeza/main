package com.bg.ticketwebshop.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bg.ticketwebshop.entity.Account;
import com.bg.ticketwebshop.entity.User;
import com.bg.ticketwebshop.repo.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

	private AccountRepository accountRepository;
	private UserService userService;

	@Autowired
	public void setAccountRepository(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	@Transactional
	public void deleteById(Long accountId) {
		accountRepository.deleteById(accountId);
	}

	/**
	 * Request the account info from account table for the username.
	 * Create a temp user (u), and fill it with the user details from the user table,
	 * based on the userName. (userName have to unique!)
	 * Than get the userId for the userName from the user table, with the getId() getter
	 * method.
	 * Based on the id, I can request the account details from the account table.
	 *  
	 */
	
	@Override
	@Transactional
	public Account findByUserName(String userName) {
		User u = userService.findByUserName(userName);
		Long userId = u.getId();
		
		Optional<Account> result = accountRepository.findById(userId);
		
		Account theAccount = null;
		
		if(result.isPresent()) {
			theAccount = result.get();
		}/*else {
			
			 throw new RuntimeException("There is no account for this user (ID / name): "
															+ userId + " / " + userName);
			
		}*/
		
		return theAccount;
	}
	
	@Override
	@Transactional
	public Account findById(Long accountId) {
		Optional<Account> result = accountRepository.findById(accountId);
		
		Account account = null;
		
		if(result.isPresent()) {
			account = result.get();
		}else {
		 throw new RuntimeException("There is no account for this user (ID): " + accountId);
		}
		return account;
	}
	
	@Override
	@Transactional
	public Account findByUserId(Long userId) {
		return accountRepository.findByUserId(userId);
		
	}

	@Override
	@Transactional
	public Double getCurrentMoney(Long userId) {
		return accountRepository.getCurrentMoney(userId);
	}
	
	@Override
	public String accountAvailabilityChecker() {
		Account act = findByUserId(userService.getLoggedUserId());
		String accNo;
		if (act == null) {
			accNo = "empty";
		}else {
			accNo = act.getAccountNumber();
		}
		System.out.println(accNo);
		return accNo;
	}

	@Override
	@Transactional
	public void save(Account account) {
		accountRepository.save(account);	
	}
	
	@Override
	@Transactional
	public void updateAccount(Double amount, Long accountId) {
		accountRepository.updateAccount(amount, accountId);
	}

}
