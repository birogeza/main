package com.bg.ticketwebshop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="event")
public class Event {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@NotNull(message="Event Name is required")
	@Column(name="event_name")
	private String eventName;
	
	@NotNull(message="Event Location is required")
	@Column(name="event_location")
	private String eventLocation;
	
	@Column(name="event_start")
	private Long eventStart;
	
	@NotNull(message="Event Start is required, expected layout: yyyy/MM/dd HH:mm:ss")
	@Pattern(regexp="^20[0-9]{2}/[0-1][0-9]/[0-3][0-9] ([0-1]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$", 
			message="Event Start is required, expected layout: yyyy/MM/dd HH:mm:ss")
	@Column(name="event_start_string")
	private String eventStartString;
	
	@Column(name="event_end")
	private Long eventEnd;
	
	@NotNull(message="Event End is required, expected layout: yyyy/MM/dd HH:mm:ss")
	@Pattern(regexp="^20[0-9]{2}/[0-1][0-9]/[0-3][0-9] ([0-1]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$", 
		message="Event End is required, expected layout: yyyy/MM/dd HH:mm:ss")
	@Column(name="event_end_string")
	private String eventEndString;
	
	@Column(name="provider_id")
	private Long providerId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getEventStart() {
		return eventStart;
	}

	public void setEventStart(Long eventStart) {
		this.eventStart = eventStart;
	}

	public Long getEventEnd() {
		return eventEnd;
	}

	public void setEventEnd(Long eventEnd) {
		this.eventEnd = eventEnd;
	}

	public Long getProviderId() {
		return providerId;
	}

	public void setProviderId(Long providerId) {
		this.providerId = providerId;
	}
	
	public String getEventStartString() {
		return eventStartString;
	}

	public void setEventStartString(String eventStartString) {
		this.eventStartString = eventStartString;
	}

	public String getEventEndString() {
		return eventEndString;
	}

	public void setEventEndString(String eventEndString) {
		this.eventEndString = eventEndString;
	}
	
	public Event(String eventName, String eventLocation, Long eventStart, String eventStartString, Long eventEnd,
			String eventEndString, Long providerId) {
		this.eventName = eventName;
		this.eventLocation = eventLocation;
		this.eventStart = eventStart;
		this.eventStartString = eventStartString;
		this.eventEnd = eventEnd;
		this.eventEndString = eventEndString;
		this.providerId = providerId;
	}

	public Event() {}

	@Override
	public String toString() {
		return "Event [id=" + id + ", eventName=" + eventName + ", eventLocation=" + eventLocation + ", eventStart="
				+ eventStart + ", eventStartString=" + eventStartString + ", eventEnd=" + eventEnd + ", eventEndString="
				+ eventEndString + ", providerId=" + providerId + "]";
	}

		
}
