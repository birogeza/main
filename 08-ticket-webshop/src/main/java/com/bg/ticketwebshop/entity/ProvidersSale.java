package com.bg.ticketwebshop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;


@IdClass(CompositeKeyProvidersSale.class)
@Entity
@Table(name="providers_sales")
public class ProvidersSale {
	
	@Id
	@Column(name="provider_id")
	private Long providerId;
	
	@Id
	@Column(name="event_name")
	private String eventName;
	
	@Column(name="price")
	private Double price;
	
	public ProvidersSale() {}

	public Long getProviderId() {
		return providerId;
	}

	public void setProviderId(Long providerId) {
		this.providerId = providerId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ProvidersSale [providerId=" + providerId + ", eventName=" + eventName + ", price=" + price + "]";
	}

	public ProvidersSale(Long providerId, String eventName, Double price) {
		this.providerId = providerId;
		this.eventName = eventName;
		this.price = price;
	}
	
	
	
}
