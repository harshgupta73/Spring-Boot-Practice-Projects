package com.example.crud3JPArelationships.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.crud3JPArelationships.dtos.req.AddStudentDTO;
import com.example.crud3JPArelationships.dtos.req.UpdateStudentDTO;
import com.example.crud3JPArelationships.dtos.res.GetStudentDTO;
import com.example.crud3JPArelationships.entity.Student;
import com.example.crud3JPArelationships.exception.DuplicateResourceException;
import com.example.crud3JPArelationships.exception.ResourceNotFoundException;
import com.example.crud3JPArelationships.mapper.StudentMapper;
import com.example.crud3JPArelationships.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository repository;
	
	@Autowired
	private StudentMapper mapper;
	
	public GetStudentDTO addStudent(AddStudentDTO addStudentDTO) {
		// TODO Auto-generated method stub
		
		if(repository.existsByEmail(addStudentDTO.getEmail())) {
			throw new DuplicateResourceException("Email already exists");
		}
		
		if(repository.existsByAadhar_AadharNumber(addStudentDTO.getAadhar().getAadharNumber())) {
			throw new DuplicateResourceException("Aadhar already exists");
		}
		
		Student savedStudent =repository.save(mapper.toEntity(addStudentDTO));
		
		return mapper.toDTO(savedStudent);
	}
	
	public GetStudentDTO updateStudent(Long id,UpdateStudentDTO dto) {
		// TODO Auto-generated method stub
		Student existing = repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Student Not found with ID:"+id));
		
		if(!existing.getEmail().equals(dto.getEmail()) && repository.existsByEmail(dto.getEmail())) {
			throw new DuplicateResourceException("Email already exists");
		}
		
		mapper.updateStudentFromDTO(dto, existing);
		Student updatedStudent =repository.save(existing);
		return mapper.toDTO(updatedStudent);
		
	}
	
	public Map<String, String> deleteStudent(Long id) {
		// TODO Auto-generated method stub
		Student existing = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Student Not found with ID:"+id));
		repository.delete(existing);
		
//		Map<String, String> response = new HashMap<String, String>();
//		response.put("message", "Student Deleted with ID:"+id);
//		
//		return response;
		//or below one line or above 3 lines
		return Map.of("message","Student Deleted with ID:"+id);
	}
	
	public GetStudentDTO getStudentById(Long id) {
		// TODO Auto-generated method stub
		Student existing = repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Student Not Found with ID:"+id));
		return mapper.toDTO(existing);
	}
	
	public List<GetStudentDTO> getAllStudents() {
		// TODO Auto-generated method stub
		return mapper.toDTOList(repository.findAll());
	}
	
	public Page<GetStudentDTO> getAllStudents(int page, int size){
		Pageable pageable = PageRequest.of(page, size);
		Page<Student> students= repository.findAll(pageable);
		
		return students.map(mapper::toDTO);
	}
	
	public Page<GetStudentDTO> getAllStudents(int page, int size, String sortBy, String direction){
		if(!direction.equalsIgnoreCase("asc") && !direction.equalsIgnoreCase("desc")) {
			throw new IllegalArgumentException("Direction must be either asc or desc");
		}
		Sort.Direction dir = Sort.Direction.fromString(direction);
		
		Pageable pageable = PageRequest.of(page, size, Sort.by(dir,sortBy));
		return repository.findAll(pageable).map(mapper::toDTO);
		
	}
	
}
