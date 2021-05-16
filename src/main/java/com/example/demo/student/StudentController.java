package com.example.demo.student;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api")
public class StudentController {
	
	List<Student> students= Arrays.asList(
			new Student(1, "Ali"),
			new Student(2,"Omar"),
			new Student(3,"Osman")
			);
	
	@GetMapping("{studentId}")
	public Student getStudent(@PathVariable("studentId") Integer id) {
		return students.stream().filter(student -> id.equals(student.getId()))
				.findFirst()
				.orElseThrow(()->new IllegalStateException(id+" doesn't exist"));
	}
	
	

}
