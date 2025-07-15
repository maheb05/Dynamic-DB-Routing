package com.dynamicrouting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dynamicrouting.entity.Student;
import com.dynamicrouting.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository repository;
	
	public List<Student> getAllStudents(){
		List<Student> sList = repository.findAll();
		return sList;
	}
}
