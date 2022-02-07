package com.luv2code.springboot.thymeleafdemo.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.luv2code.springboot.thymeleafdemo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	//metódus ami sorba rendezi az adatokat lastName alapján
	public List<Employee> findAllByOrderByLastNameAsc();
	
}
