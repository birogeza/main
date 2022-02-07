package com.bg.ticketwebshop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * CompositeKey needed, as this is a POJO for a view, where the primary key stand
 * from more the one field.
 * 
 * @author birog
 *
 */

@IdClass(CompositeKeyUsersEvents.class)
@Entity
@Table(name="users_events")
public class UsersEvents {

	@Id
	@Column(name="customer_id")
	private Long customerId;
	
	@Id
	@Column(name="provider_id")
	private Long providerId;
	
	@Id
	@Column(name="event_id")
	private Long eventId;
	
	@Id
	@Column(name="seat_id")
	private Long seatId;
	
	@Column(name="username")
	private String userName;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="event_name")
	private String eventName;
	
	@Column(name="event_location")
	private String eventLocation;
	
	@Column(name="event_start_string")
	private String eventStartString;
	
	@Column(name="seat_name")
	private String seatName;
	
	@Column(name="reserved")
	private String reserved;
	
	@Column(name="price")
	private Double price;
	
	public UsersEvents() {}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getProviderId() {
		return providerId;
	}

	public void setProviderId(Long providerId) {
		this.providerId = providerId;
	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public Long getSeatId() {
		return seatId;
	}

	public void setSeatId(Long seatId) {
		this.seatId = seatId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventLocation() {
		return eventLocation;
	}

	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}

	public String getEventStartString() {
		return eventStartString;
	}

	public void setEventStartString(String eventStartString) {
		this.eventStartString = eventStartString;
	}

	public String getSeatName() {
		return seatName;
	}

	public void setSeatName(String seatName) {
		this.seatName = seatName;
	}

	public String getReserved() {
		return reserved;
	}

	public void setReserved(String reserved) {
		this.reserved = reserved;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "UsersEvents [customerId=" + customerId + ", providerId=" + providerId + ", eventId=" + eventId
				+ ", seatId=" + seatId + ", userName=" + userName + ", firstName=" + firstName + ", lastName="
				+ lastName + ", eventName=" + eventName + ", eventLocation=" + eventLocation + ", eventStartString="
				+ eventStartString + ", seatName=" + seatName + ", reserved=" + reserved + ", price=" + price + "]";
	}

	public UsersEvents(Long customerId, Long providerId, Long eventId, Long seatId, String userName, String firstName,
			String lastName, String eventName, String eventLocation, String eventStartString, String seatName,
			String reserved, Double price) {
		this.customerId = customerId;
		this.providerId = providerId;
		this.eventId = eventId;
		this.seatId = seatId;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.eventName = eventName;
		this.eventLocation = eventLocation;
		this.eventStartString = eventStartString;
		this.seatName = seatName;
		this.reserved = reserved;
		this.price = price;
	}

	
	
	

}
