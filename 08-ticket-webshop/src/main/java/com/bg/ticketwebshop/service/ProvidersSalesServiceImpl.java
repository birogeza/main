package com.bg.ticketwebshop.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bg.ticketwebshop.entity.ProvidersSale;
import com.bg.ticketwebshop.repo.ProvidersSaleRepository;

@Service
public class ProvidersSalesServiceImpl implements ProvidersSalesService {

	private ProvidersSaleRepository providersSaleRepository;
	
	@Autowired
	public void setProvidersSaleRepository(ProvidersSaleRepository providersSaleRepository) {
		this.providersSaleRepository = providersSaleRepository;
	}
	
	@Override
	@Transactional
	public List<ProvidersSale> getProvidersSales(Long providerId) {
		return providersSaleRepository.getProvidersSales(providerId);
	}

}
