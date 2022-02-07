package com.luv2code.springboot.thymeleafdemo.controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.luv2code.springboot.thymeleafdemo.entity.Employee;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	private List<Employee> employees;
	
	@PostConstruct
	private void loadData() {
		Employee emp1 = new Employee(1, "Geza", "Biro", "geza@luv2code.com");
		Employee emp2 = new Employee(2, "Andrea", "Biro", "andrea@luv2code.com");
		Employee emp3 = new Employee(3, "Janos", "Egel", "janos@luv2code.com");	
		employees = new ArrayList<>();		
		employees.add(emp1);
		employees.add(emp2);
		employees.add(emp3);
	}
	
	@GetMapping("/list")
	public String listEmployees(Model theModel) {
		theModel.addAttribute("employees", employees);		
		return "list-employees";
	}
}
