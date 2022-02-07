package com.bg.ticketwebshop.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.bg.ticketwebshop.entity.ProvidersSale;

public interface ProvidersSaleRepository extends JpaRepository<ProvidersSale, Long> {

	@Query(value="select * from providers_sales where provider_id = ?1", nativeQuery=true)
	List<ProvidersSale> getProvidersSales(Long providerId);
	
}
