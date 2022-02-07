package com.bg.ticketwebshop.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.bg.ticketwebshop.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

	@Query(value="SELECT * FROM account a WHERE a.user_id=?1", 
			nativeQuery=true)
	Account findByUserId(Long userId);
	
	@Query(value="select sum(account_amount) from account a where a.user_id=?1", 
			nativeQuery=true)
	Double getCurrentMoney(Long userId);
	
	@Query(value="update account set account_amount=?1 where id=?2", 
			nativeQuery=true)
	void updateAccount(Double amount, Long accountId);
	
	
}
