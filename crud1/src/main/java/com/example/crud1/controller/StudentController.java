package com.example.crud1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud1.entity.Student;
import com.example.crud1.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {
	
	@Autowired
	private StudentService service;
	
	@PostMapping
	public Student addStudent(@RequestBody Student student) {
		// TODO Auto-generated method stub
		return service.addStudent(student);
	}
	
	@PutMapping("/{id}")
	public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {
		// TODO Auto-generated method stub
		return service.updateStudent(id, student);
	}
	
	@DeleteMapping("/{id}")
	public String deleteStudent(@PathVariable Long id) {
		// TODO Auto-generated method stub
		return service.deleteStudent(id);
	}
	
	@GetMapping
	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		return service.getAllStudents();
	}
	
	@GetMapping("/{id}")
	public Optional<Student> getStudentById(@PathVariable Long id) {
		// TODO Auto-generated method stub
		return service.getStudentById(id);
	}
	
	@PostMapping("/all")
	public List<Student> addAllStudents(@RequestBody List<Student> students) {
		// TODO Auto-generated method stub
		return service.addAllStudents(students);
	}
}
