package com.example.crud3JPArelationships2.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.crud3JPArelationships2.dtos.req.AddDepartmentDTO;
import com.example.crud3JPArelationships2.dtos.req.UpdateDepartmentDTO;
import com.example.crud3JPArelationships2.dtos.res.GetDepartmentDTO;
import com.example.crud3JPArelationships2.entity.Department;
import com.example.crud3JPArelationships2.exception.DepartmentInUseException;
import com.example.crud3JPArelationships2.exception.DuplicateResourceException;
import com.example.crud3JPArelationships2.exception.ResourceNotFoundException;
import com.example.crud3JPArelationships2.mapper.DepartmentMapper;
import com.example.crud3JPArelationships2.repository.DepartmentRepository;
import com.example.crud3JPArelationships2.repository.StudentRepository;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository repository;
	
	@Autowired
	private DepartmentMapper mapper;
	
	@Autowired
	private StudentRepository studentRepository;
	
	public GetDepartmentDTO addDepartment(AddDepartmentDTO addDepartmentDTO) {
		// TODO Auto-generated method stub
		if(repository.existsByName(addDepartmentDTO.getName())) {
			throw new DuplicateResourceException("Department already exists");
		}
		
		Department savedDepartment = repository.save(mapper.toEntity(addDepartmentDTO));
		
		return mapper.toDTO(savedDepartment);
		
	}
	
	public GetDepartmentDTO updateDepartment(Long id, UpdateDepartmentDTO departmentDTO) {
		// TODO Auto-generated method stub
		Department existing = repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Department Not found with ID:"+id));
		
		if(!departmentDTO.getName().equals(existing.getName()) && repository.existsByName(departmentDTO.getName())) {
			throw new DuplicateResourceException("Department already exists");
		}
		
		mapper.updateDepartmentFromDTO(departmentDTO, existing);
		Department updatedDepartment= repository.save(existing);
		return mapper.toDTO(updatedDepartment);
	}
	
	public Map<String, String> deleteDepartment(Long id) {
		// TODO Auto-generated method stub
		Department existing =repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Department Not found with ID:"+id));
		
		
		if(studentRepository.existsByDepartment_Id(id)) {
			throw new DepartmentInUseException("Department cannot be deleted because students are assigned to it");
		}
		
		repository.delete(existing);
		
		return Map.of("message","Department deleted with ID:"+id);
	}
	
	public GetDepartmentDTO getDepartmentById(Long id) {
		// TODO Auto-generated method stub
		Department existing = repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Department not found with ID:"+id));
		
		return mapper.toDTO(existing);
	}
	
	public List<GetDepartmentDTO> getAllDepartments() {
		// TODO Auto-generated method stub
		return mapper.toDTOList(repository.findAll());
	}
	
	public Page<GetDepartmentDTO> getAllDeparments(int page, int size){
		Pageable pageable = PageRequest.of(page, size);
		
		Page<Department> departments = repository.findAll(pageable);
		
		return departments.map(mapper::toDTO);
	}
	
	public Page<GetDepartmentDTO> getAllDepartments(int page,int size,String sortBy,String direction){
		if(!direction.equalsIgnoreCase("asc") && !direction.equalsIgnoreCase("desc")) {
			throw new IllegalArgumentException("Direction can be either asc or desc");
		}
		
		Sort.Direction dir= Sort.Direction.fromString(direction);
		
		Pageable pageable = PageRequest.of(page, size, Sort.by(dir, sortBy));
		
		return repository.findAll(pageable).map(mapper::toDTO);
	}
}
