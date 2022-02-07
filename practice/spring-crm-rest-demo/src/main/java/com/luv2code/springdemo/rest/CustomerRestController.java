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

	// Autowire-vel bekötjük a CustomerService-t (beinjektáljuk)
	@Autowired
	private CustomerService customerService;
	
	// hozzáadjuk a GET mappinget a "/customers" url-hez
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
	
	// adjunk mappinget a POST /customers - új customer hozzáadása
	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer theCustomer) {
		
		/*
		 * Azért kell új Customer hozzáadásánál 0-ra állítani az Id-t, mert ilyenkor
		 * a REST úgy érzékeli, hogy ez 0, vagyis null érték, ami még nincs az adatbázisban
		 * ezért a Customert újként adja hozzá. Így biztos, hogy nem írunk felül egy 
		 * már létezõ customer-t sem. Az adatbázis pedig tudja az auto-incrementálást
		 * vagyis az utolsó id után következõ számot fogja megadni customerID-nek a 
		 * táblában. 
		 */
		theCustomer.setId(0);

		customerService.saveCustomer(theCustomer);
		
		return theCustomer;
	}
	
	
	//adjunk hozzá egy új PUT mappinget - update customer:
	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer theCustomer) {
		
		customerService.saveCustomer(theCustomer);
		return theCustomer;
	}
	
	//adjunk hozzá egy Delete mappinget - delete customer
	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomer(@PathVariable int customerId) {
		
		Customer tmpCustomer = customerService.getCustomer(customerId);
		
		//ellenõrízzük, hogy a törölni kívánt Id létezik-e az adatbázisban:
		if (tmpCustomer == null) {
			throw new CustomerNotFoundException("Ez a customerID nem található "
												+ "az adatbázisban - " + customerId);
		}
		//ha létezik, töröljük:
		customerService.deleteCustomer(customerId);
		return "Az alábbi ID-jú felhasználó törölve lett: " + customerId;
	}
	
}
