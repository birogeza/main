package com.bg.springboot.cruddemo.rest;

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
import com.bg.springboot.cruddemo.entity.Employee;
import com.bg.springboot.cruddemo.service.EmployeeService;


@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	//inject employee dao
	private EmployeeService employeeService;
	
	@Autowired
	public EmployeeRestController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	//jelenítsük meg a /employees oldalon a dolgozók listáját
	@GetMapping("/employees")
	public List<Employee> findAll(){
		return employeeService.findAll();
	}
	
	
	//hozzunk létre egy új mapping-et a GET / employees/{employeeId} - Egy employee lekérdezése
	@GetMapping("employees/{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId) {
		
		Employee theEmployee = employeeService.findById(employeeId);
		
		if (theEmployee == null) {
			throw new RuntimeException("Ez a dolgozó nem található: " + employeeId);
		}
		
		return theEmployee;
	}
	
	
	//hozzunk létre POST mappinget /employees - új employee hozzáadása
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee theEmployee) {
		
		//ez most azért lett nulla, mert így biztos, hogy új employee-t ment el és nem updatel
		theEmployee.setId(0);
		employeeService.save(theEmployee);
		return theEmployee;	
	}
	
	//hozzunk létre egy PUT mappinget, hogy updatelni tudjunk egy employee-t
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee theEmployee) {
		employeeService.save(theEmployee);
		return theEmployee;
	}
	
	//töröljünk egy létező employee-t az ID alapján DELETE
	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId) {
		Employee theEmployee = employeeService.findById(employeeId);
		
		if(theEmployee == null) {
			throw new RuntimeException("Employee id not found in the database: " + employeeId);
		}
		
		employeeService.deleteById(employeeId);
		
		return "Employee törölve. EmployeeId: " + employeeId;	
	}
}

