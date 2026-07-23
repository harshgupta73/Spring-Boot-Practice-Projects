package com.example.crud3PaginationandSorting.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud3PaginationandSorting.dtos.req.AddStudentDTO;
import com.example.crud3PaginationandSorting.dtos.req.UpdateStudentDTO;
import com.example.crud3PaginationandSorting.dtos.res.GetStudentDTO;
import com.example.crud3PaginationandSorting.service.StudentService;

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
	public GetStudentDTO updateStudent(@PathVariable Long id, @Valid @RequestBody UpdateStudentDTO dto) {
		// TODO Auto-generated method stub
		return service.updateStudent(id, dto);
	}
	
	@DeleteMapping("/{id}")
	public Map<String, String> deleteStudent(@PathVariable Long id) {
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
	
	//http://localhost:8081/students/page?page=0&size=3 since we have both the methods of get all students with same name use this url
	//or comment out one method without pagination and use this url http://localhost:8081/students?page=0&size=3
	@GetMapping("/page")
	public Page<GetStudentDTO> getAllStudents(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size){
		return service.getAllStudents(page, size);
	}
	
	@GetMapping("/pagesort")
	public Page<GetStudentDTO> getAllStudents(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size, @RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "asc") String direction){
		return service.getAllStudents(page, size, sortBy, direction);
	}
	
	
}
