package com.dynamicrouting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dynamicrouting.entity.Student;
import com.dynamicrouting.service.StudentService;

@RestController
public class StudentController {
	
	@Autowired
	private StudentService service;
	
	@GetMapping("/students")
	public List<Student> getAlLStudents() {
		return service.getAllStudents();
	}
}
