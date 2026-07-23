package com.example.crud3mapstruct.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud3mapstruct.dtos.req.AddStudentDTO;
import com.example.crud3mapstruct.dtos.req.UpdateStudentDTO;
import com.example.crud3mapstruct.dtos.res.GetStudentDTO;
import com.example.crud3mapstruct.service.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/students")
public class StudentController {
	
	@Autowired
	private StudentService service;
	
	@PostMapping
	public GetStudentDTO addStudent(@Valid @RequestBody AddStudentDTO addStudentDTO) {
		// TODO Auto-generated method stub
		return service.addStudent(addStudentDTO);
	}
	
	@PutMapping("/{id}")
	public GetStudentDTO updateStudent(@PathVariable Long id,@Valid @RequestBody UpdateStudentDTO dto) {
		// TODO Auto-generated method stub
		return service.updateStudent(id,dto);
	}
	
	@GetMapping("/{id}")
	public GetStudentDTO getStudentById(@PathVariable Long id) {
		// TODO Auto-generated method stub
		return service.getStudentById(id);
	}
	
	@GetMapping
	public List<GetStudentDTO> getAllStudents() {
		// TODO Auto-generated method stub
		return service.getAllStudents();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, String>> deleteStudent(@PathVariable Long id) {
		// TODO Auto-generated method stub
		Map<String, String> response= service.deleteStudent(id);
		return ResponseEntity.ok(response);
	}
}
