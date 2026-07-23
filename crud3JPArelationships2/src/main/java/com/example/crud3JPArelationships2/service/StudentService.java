package com.example.crud3JPArelationships2.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.crud3JPArelationships2.dtos.req.AddStudentDTO;
import com.example.crud3JPArelationships2.dtos.req.UpdateStudentDTO;
import com.example.crud3JPArelationships2.dtos.res.GetStudentDTO;
import com.example.crud3JPArelationships2.entity.Department;
import com.example.crud3JPArelationships2.entity.Student;
import com.example.crud3JPArelationships2.exception.DuplicateResourceException;
import com.example.crud3JPArelationships2.exception.ResourceNotFoundException;
import com.example.crud3JPArelationships2.mapper.StudentMapper;
import com.example.crud3JPArelationships2.repository.DepartmentRepository;
import com.example.crud3JPArelationships2.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository repository;
	
	@Autowired
	private StudentMapper mapper;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	public GetStudentDTO addStudent(AddStudentDTO addStudentDTO) {
		// TODO Auto-generated method stub
		
		if(repository.existsByEmail(addStudentDTO.getEmail())) {
			throw new DuplicateResourceException("Email already exists");
		}
		
		if(repository.existsByAadhar_AadharNumber(addStudentDTO.getAadhar().getAadharNumber())) {
			throw new DuplicateResourceException("Aadhar already exists");
		}
		
		Department department = departmentRepository.findById(addStudentDTO.getDepartmentId()).orElseThrow(()->new ResourceNotFoundException("Deparment not found with ID:"+addStudentDTO.getDepartmentId()));
		
		Student student = mapper.toEntity(addStudentDTO); //does not maps the department id
		
		student.setDepartment(department);
		
		Student savedStudent = repository.save(student);
		
		return mapper.toDTO(savedStudent);
	}
	
	public GetStudentDTO updateStudent(Long id,UpdateStudentDTO dto) {
		// TODO Auto-generated method stub
		Student existing = repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Student Not found with ID:"+id));
		
		if(!dto.getEmail().equals(existing.getEmail()) && repository.existsByEmail(dto.getEmail())) {
			throw new DuplicateResourceException("Email already exists");
		}
		
		Department department = departmentRepository.findById(dto.getDepartmentId()).orElseThrow(()->new ResourceNotFoundException("Department not Found with ID:"+dto.getDepartmentId()));
		existing.setDepartment(department);
		
		
		mapper.updateStudentFromDTO(dto, existing);
		Student updatedStudent =repository.save(existing);
		return mapper.toDTO(updatedStudent);
	}
	
	public Map<String, String> deleteStudent(Long id) {
		// TODO Auto-generated method stub
		Student existing = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Student Not found with ID:"+id));
		repository.delete(existing);
		
		return Map.of("message","Student deleted with ID:"+id);
	}
	
	public GetStudentDTO getStudentById(Long id) {
		// TODO Auto-generated method stub
		Student existing = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Student Not Found with ID:"+id));
		return mapper.toDTO(existing);
	}
	
	public List<GetStudentDTO> getAllStudents() {
		// TODO Auto-generated method stub
		List<Student> students = repository.findAll();
		
		return mapper.toDTOList(students);
	}
	
	public Page<GetStudentDTO> getAllStudents(int page,int size){
		
		Pageable pageable = PageRequest.of(page, size);
		Page<Student> students = repository.findAll(pageable);
		
		return students.map(mapper::toDTO);
		
	}
	
	public Page<GetStudentDTO> getAllStudents(int page, int size, String sortBy, String direction){
		
		if(!direction.equalsIgnoreCase("asc") && !direction.equalsIgnoreCase("desc")) {
			throw new IllegalArgumentException("direction must be either asc or desc");
		}
		
		Sort.Direction dir = Sort.Direction.fromString(direction);
		System.out.println("sortBy = " + sortBy);
		System.out.println("direction = " + direction);
		Pageable pageable = PageRequest.of(page, size, Sort.by(dir,sortBy));
		
		return repository.findAll(pageable).map(mapper::toDTO);
	}
}
