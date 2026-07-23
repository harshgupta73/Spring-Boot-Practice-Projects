package com.example.crud3mapstruct.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crud3mapstruct.dtos.req.AddStudentDTO;
import com.example.crud3mapstruct.dtos.req.UpdateStudentDTO;
import com.example.crud3mapstruct.dtos.res.GetStudentDTO;
import com.example.crud3mapstruct.entity.Student;
import com.example.crud3mapstruct.exception.DuplicateResourceException;
import com.example.crud3mapstruct.exception.ResourceNotFoundException;
import com.example.crud3mapstruct.mapper.StudentMapper;
import com.example.crud3mapstruct.repository.StudentRepository;

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
		
		if(repository.existsByAadhar(addStudentDTO.getAadhar())) {
			throw new DuplicateResourceException("Aadhar already exists");
		}
		
		Student student = mapper.toEntity(addStudentDTO);
		Student savedStudent =repository.save(student);
		
		return mapper.toDTO(savedStudent);
	}
	
	public GetStudentDTO updateStudent(Long id,UpdateStudentDTO dto) {
		// TODO Auto-generated method stub
		
		Student existing = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Student not found with ID:"+id));
		
		if(!existing.getEmail().equals(dto.getEmail()) && repository.existsByEmail(dto.getEmail())) {
			throw new DuplicateResourceException("Email already exists");
		}
		
		
		
		mapper.updateStudentFromDTO(dto, existing);
		repository.save(existing);
		return mapper.toDTO(existing);
	}
	
	public GetStudentDTO getStudentById(Long id) {
		// TODO Auto-generated method stub
		Student existing = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Student not found with ID:"+id));
		return mapper.toDTO(existing);
	}
	
	public List<GetStudentDTO> getAllStudents() {
		// TODO Auto-generated method stub
		List<Student> students = repository.findAll();
		
		return mapper.toDTOList(students);
	}
	
	public Map<String, String> deleteStudent(Long id) {
		Student student=repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Student Not Found with ID:"+id));
		repository.delete(student);
		
		Map<String, String> response = new HashMap<String, String>();
		response.put("Message", "Student Deleted Successfully");
		return response;
	}
}
