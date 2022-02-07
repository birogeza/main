package com.luv2code.jackson.json.demo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Student {

	private int id;
	private String firstName;
	private String lastName;
	private boolean active;
	private Address address;
	private String[] languages;
	
	public String[] getLanguages() {
		return languages;
	}

	public void setLanguages(String[] languages) {
		this.languages = languages;
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
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
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Student(int id, String firstName, String lastName, boolean active, Address adress, String[] languages) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.active = active;
		this.address = address;
		this.languages = languages;
	}
	
	public Student() {}
	
	
	
}
