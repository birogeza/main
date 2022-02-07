package com.bg.ticketwebshop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="seat")
public class Seat {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@NotNull(message="Seat name is required!")
	@Column(name="seat_name")
	private String seatName;
	
	@Min(value=1, message="Seat price have to be a positive number!")
	@Column(name="price")
	private Double price;
	
	@Column(name="reserved")
	private boolean reserved;
	
	@Column(name="user_id")
	private Long userId;
	
	@Column(name="event_id")
	private Long eventId;
	
	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSeatName() {
		return seatName;
	}

	public void setSeatName(String seatName) {
		this.seatName = seatName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public boolean isReserved() {
		return reserved;
	}

	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Seat(String seatName, Double price, boolean reserved, Long userId, Long eventId) {
		this.seatName = seatName;
		this.price = price;
		this.reserved = reserved;
		this.userId = userId;
		this.eventId = eventId;
	}
	
	public Seat() {}

	@Override
	public String toString() {
		return "Seat [id=" + id + ", seatName=" + seatName + ", price=" + price + ", reserved=" + reserved + ", userId="
				+ userId + ", eventId=" + eventId + "]";
	}
}
