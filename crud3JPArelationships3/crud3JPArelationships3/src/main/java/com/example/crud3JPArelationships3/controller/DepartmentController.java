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

import com.example.crud3JPArelationships3.dtos.req.AddDepartmentDTO;
import com.example.crud3JPArelationships3.dtos.req.UpdateDepartmentDTO;
import com.example.crud3JPArelationships3.dtos.res.GetDepartmentDTO;
import com.example.crud3JPArelationships3.service.DepartmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@Operation(summary = "Add Department", description = "creates a new Department")
	@ApiResponses({
		@ApiResponse(responseCode = "200",description = "Department added successfully"),
		@ApiResponse(responseCode = "400",description = "validation failed"),
		@ApiResponse(responseCode = "409",description = "Department already exists")
	})
	@PostMapping
	public GetDepartmentDTO addDepartment(@Valid @RequestBody AddDepartmentDTO addDepartmentDTO) {
		// TODO Auto-generated method stub
		return departmentService.addDepartment(addDepartmentDTO);
	}
	
	@Operation(summary = "Update Department",description = "Updates an existing Department")
	@ApiResponses({
		@ApiResponse(responseCode = "200",description = "Department updated successfully"),
		@ApiResponse(responseCode = "400",description = "validation failed"),
		@ApiResponse(responseCode = "404",description = "Department not found"),
		@ApiResponse(responseCode = "409",description = "Department already exists")
	})
	@PutMapping("/{id}")
	public GetDepartmentDTO updateDepartment(@PathVariable Long id, @Valid @RequestBody UpdateDepartmentDTO departmentDTO) {
		// TODO Auto-generated method stub
		return departmentService.updateDepartment(id, departmentDTO);
	}
	
	
	@Operation(summary = "Delete Department",description = "Deletes an existing Department")
	@ApiResponses({
		@ApiResponse(responseCode = "200",description = "Department deleted successfully"),
		@ApiResponse(responseCode = "404",description = "Department Not found")
	})
	@DeleteMapping("/{id}")
	public Map<String, String> deleteDepartment(@PathVariable Long id) {
		// TODO Auto-generated method stub
		return departmentService.deleteDepartment(id);
	}
	
	@Operation(summary = "Get Department",description = "Get Department By its ID")
	@ApiResponses({
		@ApiResponse(responseCode = "200",description = "Department found successfully"),
		@ApiResponse(responseCode = "404",description = "Department not found")
	})
	@GetMapping("/{id}")
	public GetDepartmentDTO getDepartmentById(@PathVariable Long id) {
		// TODO Auto-generated method stub
		return departmentService.getDepartmentByID(id);
	}
	
	@Operation(summary = "Get Departments",description = "Get All existing Departments")
	@ApiResponses({
		@ApiResponse(responseCode = "200",description = "Departments retrieved successfully"),
	})
	@GetMapping
	public List<GetDepartmentDTO> getAllDepartments() {
		// TODO Auto-generated method stub
		return departmentService.getAllDepartments();
	}
	
	@Operation(summary = "Get Departments",description = "Get Departments with page")
	@ApiResponses({
		@ApiResponse(responseCode = "200",description = "Departments retrieved successfully with page")
	})
	@GetMapping("/page")
	public Page<GetDepartmentDTO> getAllDepartments(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size){
		return departmentService.getAllDepartments(page, size);
	}
	
	@Operation(summary = "Get Departments", description = "Get sorted Departments with page")
	@ApiResponses({
		@ApiResponse(responseCode = "200",description = "sorted Departments retrieved successfully"),
		@ApiResponse(responseCode = "400",description = "Invalid page number,size or sorting field")
	})
	@GetMapping("/pagesorted")
	public Page<GetDepartmentDTO> getAllDepartments(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size, @RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "desc") String direction){
		return departmentService.getAllDepartments(page, size, sortBy, direction);
	}
}
