package com.bg.ticketwebshop.service;

import java.util.List;
import com.bg.ticketwebshop.entity.ProvidersSale;

public interface ProvidersSalesService {

	List<ProvidersSale> getProvidersSales(Long providerId);
	
}
