package com.bg.springboot.cruddemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bg.springboot.cruddemo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
