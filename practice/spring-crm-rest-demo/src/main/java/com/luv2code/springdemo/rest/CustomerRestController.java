package com.luv2code.springdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

	// Autowire-vel bek�tj�k a CustomerService-t (beinjekt�ljuk)
	@Autowired
	private CustomerService customerService;
	
	// hozz�adjuk a GET mappinget a "/customers" url-hez
	@GetMapping("/customers")
	public List<Customer> getCustomers(){
		
		return customerService.getCustomers();
	}
	
	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) {
		
		Customer customer = customerService.getCustomer(customerId);
		
		if (customer == null) {
			throw new CustomerNotFoundException("Customer id not found - " + customerId);
		}
		
		return customer;	
	}
	
	// adjunk mappinget a POST /customers - �j customer hozz�ad�sa
	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer theCustomer) {
		
		/*
		 * Az�rt kell �j Customer hozz�ad�s�n�l 0-ra �ll�tani az Id-t, mert ilyenkor
		 * a REST �gy �rz�keli, hogy ez 0, vagyis null �rt�k, ami m�g nincs az adatb�zisban
		 * ez�rt a Customert �jk�nt adja hozz�. �gy biztos, hogy nem �runk fel�l egy 
		 * m�r l�tez� customer-t sem. Az adatb�zis pedig tudja az auto-increment�l�st
		 * vagyis az utols� id ut�n k�vetkez� sz�mot fogja megadni customerID-nek a 
		 * t�bl�ban. 
		 */
		theCustomer.setId(0);

		customerService.saveCustomer(theCustomer);
		
		return theCustomer;
	}
	
	
	//adjunk hozz� egy �j PUT mappinget - update customer:
	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer theCustomer) {
		
		customerService.saveCustomer(theCustomer);
		return theCustomer;
	}
	
	//adjunk hozz� egy Delete mappinget - delete customer
	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomer(@PathVariable int customerId) {
		
		Customer tmpCustomer = customerService.getCustomer(customerId);
		
		//ellen�r�zz�k, hogy a t�r�lni k�v�nt Id l�tezik-e az adatb�zisban:
		if (tmpCustomer == null) {
			throw new CustomerNotFoundException("Ez a customerID nem tal�lhat� "
												+ "az adatb�zisban - " + customerId);
		}
		//ha l�tezik, t�r�lj�k:
		customerService.deleteCustomer(customerId);
		return "Az al�bbi ID-j� felhaszn�l� t�r�lve lett: " + customerId;
	}
	
}
