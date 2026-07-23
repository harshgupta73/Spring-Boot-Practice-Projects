package com.example.crud3JPArelationships3.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.crud3JPArelationships3.dtos.req.AddDepartmentDTO;
import com.example.crud3JPArelationships3.dtos.req.UpdateDepartmentDTO;
import com.example.crud3JPArelationships3.dtos.res.GetDepartmentDTO;
import com.example.crud3JPArelationships3.entity.Department;
import com.example.crud3JPArelationships3.exception.DepartmentInUseException;
import com.example.crud3JPArelationships3.exception.DuplicateResourceException;
import com.example.crud3JPArelationships3.exception.ResourceNotFoundException;
import com.example.crud3JPArelationships3.mapper.DepartmentMapper;
import com.example.crud3JPArelationships3.repository.DepartmentRepository;
import com.example.crud3JPArelationships3.repository.StudentRepository;

@Service
public class DepartmentService {
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private DepartmentMapper departmentMapper;
	
	@Autowired
	private StudentRepository studentRepository;
	
	public GetDepartmentDTO addDepartment(AddDepartmentDTO addDepartmentDTO) {
		// TODO Auto-generated method stub
		
		if(departmentRepository.existsByName(addDepartmentDTO.getName())) {
			throw new DuplicateResourceException("Department already exists");
		}
		
		Department department = departmentMapper.toEntity(addDepartmentDTO);
		return departmentMapper.toDTO(departmentRepository.save(department)); 
	}
	
	public GetDepartmentDTO updateDepartment(Long id,UpdateDepartmentDTO departmentDTO) {
		// TODO Auto-generated method stub
		Department existing = departmentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Department not found with ID:"+id));
		
		if(!departmentDTO.getName().equals(existing.getName()) && departmentRepository.existsByName(departmentDTO.getName())) {
			throw new DuplicateResourceException("Department Already exists");
		}
		
		departmentMapper.updateDepartmentFromDTO(departmentDTO, existing);
		Department updatedDepartment = departmentRepository.save(existing);
		return departmentMapper.toDTO(updatedDepartment);
	}
	
	public Map<String, String> deleteDepartment(Long id) {
		// TODO Auto-generated method stub
		Department existing= departmentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Department Not found with ID:"+id));
		
		if(studentRepository.existsByDepartment_Id(id)) {
			throw new DepartmentInUseException("Department cannot be deleted because students are assigned to it");
		}
		
		departmentRepository.delete(existing);
		
		return Map.of("message","Department deleted with ID:"+id);
		
	}
	
	public GetDepartmentDTO getDepartmentByID(Long id) {
		// TODO Auto-generated method stub
		Department existing = departmentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Department Not found with ID:"+id));
		
		return departmentMapper.toDTO(existing);
	}
	
	public List<GetDepartmentDTO> getAllDepartments() {
		// TODO Auto-generated method stub
		return departmentMapper.toDTOList(departmentRepository.findAll());
	}
	
	public Page<GetDepartmentDTO> getAllDepartments(int page, int size){
		Pageable pageable = PageRequest.of(page, size);
		
		return departmentRepository.findAll(pageable).map(departmentMapper::toDTO);
	}
	
	public Page<GetDepartmentDTO> getAllDepartments(int page, int size, String sortBy, String direction){
		
		if(!direction.equalsIgnoreCase("asc") && !direction.equalsIgnoreCase("desc")) {
			throw new IllegalArgumentException("direction can be either asc or desc");
		}
		
		Sort.Direction dir = Sort.Direction.fromString(direction);
		
		Pageable pageable = PageRequest.of(page, size, Sort.by(dir, sortBy));
		
		return departmentRepository.findAll(pageable).map(departmentMapper::toDTO);
	}
}
