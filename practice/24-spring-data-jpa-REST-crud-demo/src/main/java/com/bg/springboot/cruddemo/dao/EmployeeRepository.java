package com.bg.springboot.cruddemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.bg.springboot.cruddemo.entity.Employee;

@RepositoryRestResource(path="member")
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
