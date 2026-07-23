package com.example.crud2withDTO.controller;

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

import com.example.crud2withDTO.DTO.StudentDTO;
import com.example.crud2withDTO.Service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {
	
	@Autowired
	private StudentService service;
	
	@PostMapping
	public StudentDTO addStudent(@RequestBody StudentDTO dto) {
		// TODO Auto-generated method stub
		return service.addStudent(dto);
	}
	
	@PutMapping("/{id}")
	public StudentDTO updateStudent(@PathVariable Long id, @RequestBody StudentDTO dto) {
		return service.updateStudent(id, dto);
	}
	
	@DeleteMapping("/{id}")
	public String deleteStudent(@PathVariable Long id) {
		// TODO Auto-generated method stub
		return service.deleteStudent(id);
	}
	
	@GetMapping
	public List<StudentDTO> getAllStudents() {
		// TODO Auto-generated method stub
		return service.getAllStudents();
	}
	
	@GetMapping("/{id}")
	public StudentDTO getStudentById(@PathVariable Long id) {
		// TODO Auto-generated method stub
		return service.getStudentById(id);
	}
	
}
