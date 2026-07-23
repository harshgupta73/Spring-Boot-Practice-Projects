package com.example.crud3withDTOs.controller;

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

import com.example.crud3withDTOs.DTOs.req.AddStudentDTO;
import com.example.crud3withDTOs.DTOs.req.UpdateStudentDTO;
import com.example.crud3withDTOs.DTOs.res.GetStudentDTO;
import com.example.crud3withDTOs.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {
	
	@Autowired
	private StudentService service;
	
	@PostMapping
	public GetStudentDTO addStudent(@RequestBody AddStudentDTO addStudentDTO) {
		// TODO Auto-generated method stub
		return service.addStudent(addStudentDTO);
	}
	
	@PutMapping("/{id}")
	public GetStudentDTO updateStudent(@PathVariable Long id, @RequestBody UpdateStudentDTO dto) {
		// TODO Auto-generated method stub
		return service.updateStudent(id, dto);
	}
	
	@DeleteMapping("/{id}")
	public String deleteStudent(@PathVariable Long id) {
		// TODO Auto-generated method stub
		return service.deleteStudent(id);
	}
	
	@GetMapping
	public List<GetStudentDTO> getAllStudents() {
		// TODO Auto-generated method stub
		return service.getAllStudents();
	}
	
	@GetMapping("/{id}")
	public GetStudentDTO getStudentById(@PathVariable Long id) {
		// TODO Auto-generated method stub
		return service.getStudentById(id);
	}
}
