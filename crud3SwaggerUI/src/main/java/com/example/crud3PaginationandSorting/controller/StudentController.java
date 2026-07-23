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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/students")
public class StudentController {
	
	@Autowired
	private StudentService service;
	
	@Operation(summary = "Add Student",description = "Creates a new student")
	@ApiResponses({
	    @ApiResponse(responseCode = "200", description = "Student added successfully"),
	    @ApiResponse(responseCode = "400", description = "Validation failed"),
	    @ApiResponse(responseCode = "409", description = "Email or Aadhaar already exists")
	})
	@PostMapping
	public GetStudentDTO addStudent(@Valid @RequestBody AddStudentDTO addStudentDTO) {
		// TODO Auto-generated method stub
		return service.addStudent(addStudentDTO);
	}
	
	@Operation(summary = "Update Student",description = "Updates an existing student")
	@ApiResponses({
	    @ApiResponse(responseCode = "200", description = "Student updated successfully"),
	    @ApiResponse(responseCode = "400", description = "Validation failed"),
	    @ApiResponse(responseCode = "404", description = "Student not found"),
	    @ApiResponse(responseCode = "409", description = "Email already exists")
	})
	@PutMapping("/{id}")
	public GetStudentDTO updateStudent(@PathVariable Long id, @Valid @RequestBody UpdateStudentDTO dto) {
		// TODO Auto-generated method stub
		return service.updateStudent(id, dto);
	}
	
	@Operation(summary = "Delete Student",description = "Deletes an existing student")
	@ApiResponses({
	    @ApiResponse(responseCode = "200", description = "Student deleted successfully"),
	    @ApiResponse(responseCode = "404", description = "Student not found")
	})
	@DeleteMapping("/{id}")
	public Map<String, String> deleteStudent(@PathVariable Long id) {
		// TODO Auto-generated method stub
		return service.deleteStudent(id);
	}
	
	@Operation(summary = "Get All Student",description = "Displays all student")
	@GetMapping
	@ApiResponses({
	    @ApiResponse(responseCode = "200", description = "Students retrieved successfully")
	})
	public List<GetStudentDTO> getAllStudents() {
		// TODO Auto-generated method stub
		return service.getAllStudents();
	}
	
	
	@Operation(summary = "Get Student By Id",description = "Display student By id")
	@ApiResponses({
	    @ApiResponse(responseCode = "200", description = "Student found successfully"),
	    @ApiResponse(responseCode = "404", description = "Student not found")
	})
	@GetMapping("/{id}")
	public GetStudentDTO getStudentById(@PathVariable Long id) {
		// TODO Auto-generated method stub
		return service.getStudentById(id);
	}
	
	//http://localhost:8081/students/page?page=0&size=3 since we have both the methods of get all students with same name use this url
	//or comment out one method without pagination and use this url http://localhost:8081/students?page=0&size=3
	@Operation(summary = "Display using page",description = "Display students using page")
	@ApiResponses({
	    @ApiResponse(responseCode = "200", description = "Students retrieved successfully")
	})
	@GetMapping("/page")
	public Page<GetStudentDTO> getAllStudents(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size){
		return service.getAllStudents(page, size);
	}
	
	@Operation(summary = "Disply sorted students",description = "Display sorted students using pagination and sorting")
	@ApiResponses({
	    @ApiResponse(responseCode = "200", description = "Students retrieved successfully"),
	    @ApiResponse(responseCode = "400", description = "Invalid page number, size or sorting field")
	})
	@GetMapping("/pagesort")
	public Page<GetStudentDTO> getAllStudents(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size, @RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "asc") String direction){
		return service.getAllStudents(page, size, sortBy, direction);
	}
	
	
}
