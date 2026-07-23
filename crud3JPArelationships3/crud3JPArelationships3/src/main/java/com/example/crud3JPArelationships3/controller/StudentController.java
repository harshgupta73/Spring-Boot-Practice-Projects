package com.example.crud3JPArelationships3.controller;

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

import com.example.crud3JPArelationships3.dtos.req.AddStudentDTO;
import com.example.crud3JPArelationships3.dtos.req.UpdateStudentDTO;
import com.example.crud3JPArelationships3.dtos.res.GetStudentDTO;
import com.example.crud3JPArelationships3.service.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/students")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@Operation(summary = "Add Student", description = "creates a new Student")
	@ApiResponses({
		@ApiResponse(responseCode = "200",description = "Student added successfully"),
		@ApiResponse(responseCode = "400",description = "validation failed"),
		 @ApiResponse(responseCode = "404", description = "Department not found"),
		@ApiResponse(responseCode = "409",description = "Email or aadhar already exists")
	})
	@PostMapping
	public GetStudentDTO addStudent(@Valid @RequestBody AddStudentDTO addStudentDTO) {
		// TODO Auto-generated method stub
		return studentService.addStudent(addStudentDTO);
	}
	
	
	@Operation(summary = "Update Student",description = "Updates an existing student")
	@ApiResponses({
		@ApiResponse(responseCode = "200",description = "student updated successfully"),
		@ApiResponse(responseCode = "400",description = "validation failed"),
		@ApiResponse(responseCode = "404",description = "Student not found"),
		@ApiResponse(responseCode = "409",description = "Email already exists")
	})
	@PutMapping("/{id}")
	public GetStudentDTO updateStudent(@PathVariable Long id, @Valid @RequestBody UpdateStudentDTO dto) {
		// TODO Auto-generated method stub
		return studentService.updateStudent(id, dto);
	}
	
	
	@Operation(summary = "Delete Student",description = "Deletes an existing student")
	@ApiResponses({
		@ApiResponse(responseCode = "200",description = "Student deleted successfully"),
		@ApiResponse(responseCode = "404",description = "Student Not found")
	})
	@DeleteMapping("/{id}")
	public Map<String, String> deleteStudent(@PathVariable Long id) {
		// TODO Auto-generated method stub
		return studentService.deleteStudent(id);
	}
	
	
	@Operation(summary = "Get Student",description = "Get Student By its ID")
	@ApiResponses({
		@ApiResponse(responseCode = "200",description = "Student found successfully"),
		@ApiResponse(responseCode = "404",description = "Student not found")
	})
	@GetMapping("/{id}")
	public GetStudentDTO getStudentById(@PathVariable Long id) {
		// TODO Auto-generated method stub
		return studentService.getStudentById(id);
	}
	
	@Operation(summary = "Get Students",description = "Get All existing students")
	@ApiResponses({
		@ApiResponse(responseCode = "200",description = "Students retrieved successfully"),
	})
	@GetMapping
	public List<GetStudentDTO> getAllStudents() {
		// TODO Auto-generated method stub
		return studentService.getAllStudents();
	}
	
	@Operation(summary = "Get Students",description = "Get students with page")
	@ApiResponses({
		@ApiResponse(responseCode = "200",description = "students retrieved successfully with page")
	})
	@GetMapping("/page")
	public Page<GetStudentDTO> getAllStudents(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size){
		return studentService.getAllStudents(page, size);
	}
	
	@Operation(summary = "Get Students", description = "Get sorted students with page")
	@ApiResponses({
		@ApiResponse(responseCode = "200",description = "sorted students retrieved successfully"),
		@ApiResponse(responseCode = "400",description = "Invalid page number,size or sorting field")
	})
	@GetMapping("/pagesorted")
	public Page<GetStudentDTO> getAllStudents(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size, @RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "desc") String direction){
		return studentService.getAllStudents(page, size, sortBy, direction);
	}
}
