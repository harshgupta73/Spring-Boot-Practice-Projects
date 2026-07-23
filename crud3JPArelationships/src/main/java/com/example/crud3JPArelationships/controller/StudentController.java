package com.example.crud3JPArelationships.controller;

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

import com.example.crud3JPArelationships.dtos.req.AddStudentDTO;
import com.example.crud3JPArelationships.dtos.req.UpdateStudentDTO;
import com.example.crud3JPArelationships.dtos.res.GetStudentDTO;
import com.example.crud3JPArelationships.service.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/students")
public class StudentController {
	
	@Autowired
	private StudentService service;
	
	
	@Operation(summary = "Add Students",description = "creates a new student")
	@ApiResponses({
		@ApiResponse(responseCode = "200",description = "Student successfully added"),
		@ApiResponse(responseCode = "400",description = "Validation Falied"),
		@ApiResponse(responseCode = "409",description = "Email or Aadhar already exists")
	})
	@PostMapping
	public GetStudentDTO addStudent(@Valid @RequestBody AddStudentDTO addStudentDTO) {
		// TODO Auto-generated method stub
		return service.addStudent(addStudentDTO);
	}
	
	@Operation(summary = "Update Student",description = "Updates an existing student")
	@ApiResponses({
		@ApiResponse(responseCode = "200",description = "Student updated successfully"),
		@ApiResponse(responseCode = "400",description = "Validation failed"),
		@ApiResponse(responseCode = "404",description = "Student Not Found"),
		@ApiResponse(responseCode = "409",description = "Email  already exists")
	})
	@PutMapping("/{id}")
	public GetStudentDTO updateStudent(@PathVariable Long id, @Valid @RequestBody UpdateStudentDTO dto) {
		// TODO Auto-generated method stub
		return service.updateStudent(id, dto);
	}
	
	@Operation(summary = "Delete Student",description = "Delete an existing student")
	@ApiResponses({
		@ApiResponse(responseCode = "200",description = "Student deleted successfully"),
		@ApiResponse(responseCode = "404",description = "Student Not Found")
	})
	@DeleteMapping("/{id}")
	public Map<String, String> deleteStudent(@PathVariable Long id) {
		// TODO Auto-generated method stub
		return service.deleteStudent(id);
	}
	
	
	@Operation(summary = "Get Student",description = "Get a student by its ID")
	@ApiResponses({
		@ApiResponse(responseCode = "200",description = "Student retrieved Successfully"),
		@ApiResponse(responseCode = "404",description = "Student Not Found")
	})
	@GetMapping("/{id}")
	public GetStudentDTO getStudentById(@PathVariable Long id) {
		// TODO Auto-generated method stub
		return service.getStudentById(id);
	}
	
	
	@Operation(summary = "Get Students",description = "Get All Students")
	@ApiResponses({
		@ApiResponse(responseCode = "200",description = "retrieved all the existing Students")
	})
	@GetMapping
	public List<GetStudentDTO> getAllStudents() {
		// TODO Auto-generated method stub
		return service.getAllStudents();
	}
	
	
	@Operation(summary = "Get Students Page",description = "Get Students with Page")
	@ApiResponses({
		@ApiResponse(responseCode = "200",description = "retrieved all the existing Students with page")
	})
	@GetMapping("/page")
	public Page<GetStudentDTO> getAllStudents(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size){
		return service.getAllStudents(page,size);
	}
	
	@Operation(summary = "Get Sorted Students",description = "Get Sorted Students with page")
	@ApiResponses({
		@ApiResponse(responseCode = "200",description = "retrieved all the existing students with sort and page"),
		@ApiResponse(responseCode = "400",description = "Invalid page number,size or sorting field")
	})
	@GetMapping("/pagesort")
	public Page<GetStudentDTO> getAllStudents(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size, @RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "asc") String direction){
		return service.getAllStudents(page, size, sortBy, direction);
	}
}
