package com.example.crud3Exception.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.crud3Exception.dtos.req.AddStudentDTO;
import com.example.crud3Exception.dtos.req.UpdateStudentDTO;
import com.example.crud3Exception.dtos.res.GetStudentDTO;
import com.example.crud3Exception.entity.Student;
import com.example.crud3Exception.exception.ResourceNotFoundException;
import com.example.crud3Exception.repository.StudentRepository;


@Service
public class StudentService {
	
	@Autowired
	private StudentRepository repository;
	
	public GetStudentDTO addStudent(AddStudentDTO addStudentDTO) {
		// TODO Auto-generated method stub
		
		Student student = mapToEntity(addStudentDTO);
		
		Student savedStudent =  repository.save(student);
		
		GetStudentDTO response = mapToDTO(savedStudent);
		
		return response;
		
	}
	
	public GetStudentDTO updateStudent(Long id,UpdateStudentDTO dto) {
		// TODO Auto-generated method stub
		
		Student existing = repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Student Not Found with id:"+id));
		
		Student student =mapToEntity(dto, existing);
		
		Student updatedStudent =repository.save(student);
		
		GetStudentDTO response = mapToDTO(updatedStudent);
		
		return response;
	}
	
	
	public String deleteStudent(Long id) {
		// TODO Auto-generated method stub
		Student student = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Student not found with id:"+id));
		repository.delete(student);
		
		return "student deleted";
	}
	
	
	public List<GetStudentDTO> getAllStudents() {
		// TODO Auto-generated method stub
		List<Student> students = repository.findAll();
		
		List<GetStudentDTO> dtos = new ArrayList<GetStudentDTO>();
		
		for(Student student:students) {
			dtos.add(mapToDTO(student));
		}
		
		return dtos;
	}
	
	
	public GetStudentDTO getStudentById(Long id) {
		// TODO Auto-generated method stub
		Student student = repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Student Not found with id:"+id));
		
		return mapToDTO(student);
	}
	
	
	public Student mapToEntity(AddStudentDTO addStudentDTO) {
		// TODO Auto-generated method stub
		return Student.builder()
				.name(addStudentDTO.getName())
				.email(addStudentDTO.getEmail())
				.course(addStudentDTO.getCourse())
				.aadhar(addStudentDTO.getAadhar())
				.build();
	}
	
	
	public Student mapToEntity(UpdateStudentDTO updateStudentDTO, Student existing) {
		// TODO Auto-generated method stub
		return Student.builder()
				.id(existing.getId())
				.name(updateStudentDTO.getName())
				.email(updateStudentDTO.getEmail())
				.course(updateStudentDTO.getCourse())
				.build();
	}
	
	public GetStudentDTO mapToDTO(Student student) {
		// TODO Auto-generated method stub
		
		return GetStudentDTO.builder()
				.name(student.getName())
				.email(student.getEmail())
				.course(student.getCourse())
				.build();
	}
	
}
