package com.example.crud3JPArelationships2.controller;

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

import com.example.crud3JPArelationships2.dtos.req.AddDepartmentDTO;
import com.example.crud3JPArelationships2.dtos.req.UpdateDepartmentDTO;
import com.example.crud3JPArelationships2.dtos.res.GetDepartmentDTO;
import com.example.crud3JPArelationships2.service.DepartmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
	
	@Autowired
	private DepartmentService service;
	
	@PostMapping
	public GetDepartmentDTO addDepartment(@Valid @RequestBody AddDepartmentDTO addDepartmentDTO) {
		// TODO Auto-generated method stub
		return service.addDepartment(addDepartmentDTO);
	}
	
	@PutMapping("/{id}")
	public GetDepartmentDTO updateDepartment(@PathVariable Long id, @Valid @RequestBody UpdateDepartmentDTO departmentDTO) {
		// TODO Auto-generated method stub
		return service.updateDepartment(id, departmentDTO);
	}
	
	@DeleteMapping("/{id}")
	public Map<String, String> deleteDepartment(@PathVariable Long id) {
		// TODO Auto-generated method stub
		return service.deleteDepartment(id);
	}
	
	@GetMapping("/{id}")
	public GetDepartmentDTO getDepartmentById(@PathVariable Long id) {
		// TODO Auto-generated method stub
		return service.getDepartmentById(id);
	}
	
	@GetMapping
	public List<GetDepartmentDTO> getAllDepartments() {
		// TODO Auto-generated method stub
		return service.getAllDepartments();
	}
	
	@GetMapping("/page")
	public Page<GetDepartmentDTO> getAllDepartments(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size){
		return service.getAllDeparments(page, size);
	}
	
	@GetMapping("/pagesort")
	public Page<GetDepartmentDTO> getAllDepartments(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2")  int size, @RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "asc")String direction){
		return service.getAllDepartments(page, size, sortBy, direction);
	}
	
}
