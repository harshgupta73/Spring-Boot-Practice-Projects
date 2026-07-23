package com.example.crud3JPArelationships3.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.crud3JPArelationships3.dtos.req.AddStudentDTO;
import com.example.crud3JPArelationships3.dtos.req.UpdateStudentDTO;
import com.example.crud3JPArelationships3.dtos.res.GetStudentDTO;
import com.example.crud3JPArelationships3.entity.Department;
import com.example.crud3JPArelationships3.entity.Student;
import com.example.crud3JPArelationships3.exception.DuplicateResourceException;
import com.example.crud3JPArelationships3.exception.ResourceNotFoundException;
import com.example.crud3JPArelationships3.mapper.StudentMapper;
import com.example.crud3JPArelationships3.repository.DepartmentRepository;
import com.example.crud3JPArelationships3.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private StudentMapper studentMapper;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	public GetStudentDTO addStudent(AddStudentDTO addStudentDTO) {
		// TODO Auto-generated method stub
		
		if(studentRepository.existsByEmail(addStudentDTO.getEmail())) {
			throw new DuplicateResourceException("Email already exists");
		}
		
		if(studentRepository.existsByAadhar_AadharNumber(addStudentDTO.getAadhar().getAadharNumber())) {
			throw new DuplicateResourceException("Aadhar already exists");
		}
		
		Student student= studentMapper.toEntity(addStudentDTO); //does not maps department due to different data types while mapping in entity and dto
		
		Department department = departmentRepository.findById(addStudentDTO.getDepartmentId()).orElseThrow(()-> new ResourceNotFoundException("Department Not Found with ID:"+addStudentDTO.getDepartmentId()));
		student.setDepartment(department); //maps department
		return studentMapper.toDTO(studentRepository.save(student));
	}
	
	public GetStudentDTO updateStudent(Long id, UpdateStudentDTO dto) {
		
		Student existing =studentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Student Not found with ID:"+id));
		
		// TODO Auto-generated method stub
		if(!dto.getEmail().equals(existing.getEmail()) && studentRepository.existsByEmail(dto.getEmail())) {
			throw new DuplicateResourceException("Email already exists");
		}
		
		Department department = departmentRepository.findById(dto.getDepartmentId()).orElseThrow(()-> new ResourceNotFoundException("Department Not found with ID:"+dto.getDepartmentId()));
		existing.setDepartment(department);
		
		studentMapper.updateStudentFromDTO(dto, existing);
		Student updatedStudent= studentRepository.save(existing);
		return studentMapper.toDTO(updatedStudent);
		
		
	}
	
	public Map<String, String> deleteStudent(Long id) {
		// TODO Auto-generated method stub
		Student existing= studentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Student Not found with ID:"+id));
		studentRepository.delete(existing);
		
		return Map.of("message","Student Deleted with ID:"+id);
	}
	
	public GetStudentDTO getStudentById(Long id) {
		// TODO Auto-generated method stub
		Student existing= studentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("student not found with ID:"+id));
		
		return studentMapper.toDTO(existing);
	}
	
	public List<GetStudentDTO> getAllStudents() {
		// TODO Auto-generated method stub
		List<Student> existingStudents =studentRepository.findAll();
		return studentMapper.toDTOList(existingStudents);
	}
	
	public Page<GetStudentDTO> getAllStudents(int page,int size){
		Pageable pageable = PageRequest.of(page, size);
		
		Page<Student> existingStudents =studentRepository.findAll(pageable);
		
		return existingStudents.map(studentMapper::toDTO);
	}
	
	public Page<GetStudentDTO> getAllStudents(int page, int size, String sortBy, String direction){
		
		if(!direction.equalsIgnoreCase("asc") && !direction.equalsIgnoreCase("desc")) {
			throw new IllegalArgumentException("Direction can be either asc or desc");
		}
		
		Sort.Direction dir = Sort.Direction.fromString(direction);
		
		Pageable pageable = PageRequest.of(page, size, Sort.by(dir, sortBy));
		
		Page<Student> existingStudents = studentRepository.findAll(pageable);
		return existingStudents.map(studentMapper::toDTO);
	}
}
