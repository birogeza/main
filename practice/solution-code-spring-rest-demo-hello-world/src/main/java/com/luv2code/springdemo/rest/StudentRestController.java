package com.luv2code.springdemo.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.luv2code.springdemo.entity.Student;
import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api")
public class StudentRestController {

	private List<Student> students;
	
	// define @PostConstruct to load student data ONLY once!
	@PostConstruct
	public void loadData() {
		students = new ArrayList<>();
		this.students.add(new Student("Poornima", "Patel"));
		this.students.add(new Student("Mary", "Smith"));
		this.students.add(new Student("Mario", "Rossi"));
	}
		
	@GetMapping("/student")
	public List<Student> getStudents(){
		
		return this.students;
	}
	
	// hozzunk létre egy új EndPoint-ot a "/student/{studnetId}"-nak
	// ez egy Student-et ad vissza index alapján
	@GetMapping("/student/{studentId}")
	public Student getStudnet(@PathVariable int studentId) {
		
		// vizsgáljuk meg a studentId-t a listahoz képest
		if(studentId >= students.size() || studentId < 0) {
			throw new StudentNotFoundException("Student is not found - " + studentId);
		}
		
		return students.get(studentId);
	}
}
