package com.example.crud3withDTOs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crud3withDTOs.DTOs.req.AddStudentDTO;
import com.example.crud3withDTOs.DTOs.req.UpdateStudentDTO;
import com.example.crud3withDTOs.DTOs.res.GetStudentDTO;
import com.example.crud3withDTOs.entity.Student;
import com.example.crud3withDTOs.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository repository;
	
	public GetStudentDTO addStudent(AddStudentDTO addStudentDTO) {
		// TODO Auto-generated method stub
		Student student = new Student();
		student.setName(addStudentDTO.getName());
		student.setEmail(addStudentDTO.getEmail());
		student.setCourse(addStudentDTO.getCourse());
		student.setAadhar(addStudentDTO.getAadhar());
		
		Student savedStudent = repository.save(student);
		
		GetStudentDTO response = new GetStudentDTO();
		response.setName(savedStudent.getName());
		response.setEmail(savedStudent.getEmail());
		response.setCourse(savedStudent.getCourse());
		
		return response;
	}
	
	public GetStudentDTO updateStudent(Long id,UpdateStudentDTO dto) {
		// TODO Auto-generated method stub
		Student existing = repository.findById(id).orElseThrow(() -> new RuntimeException("Student Not Found"));
		
		existing.setName(dto.getName());
		existing.setEmail(dto.getEmail());
		existing.setCourse(dto.getCourse());
		
		Student updatedStudent = repository.save(existing);
		
		GetStudentDTO response = new GetStudentDTO();
		response.setName(updatedStudent.getName());
		response.setEmail(updatedStudent.getEmail());
		response.setCourse(updatedStudent.getCourse());
		
		return response;
		
	}
	
	public String deleteStudent(Long id) {
		// TODO Auto-generated method stub
		Student existing =  repository.findById(id).orElseThrow(()-> new RuntimeException("Student not found"));
		repository.delete(existing);
		return "Student Deleted";
	}
	
	public List<GetStudentDTO> getAllStudents() {
		// TODO Auto-generated method stub
		List<Student> students = repository.findAll();
		
		List<GetStudentDTO> response = new ArrayList<GetStudentDTO>();
		
		for(Student student: students) {
			GetStudentDTO getStudentDTO = new GetStudentDTO();
			getStudentDTO.setName(student.getName());
			getStudentDTO.setEmail(student.getEmail());
			getStudentDTO.setCourse(student.getCourse());
			
			response.add(getStudentDTO);
		}
		
		return response;
	}
	
	public GetStudentDTO getStudentById(Long id) {
		// TODO Auto-generated method stub
		Student student = repository.findById(id).orElseThrow(()-> new RuntimeException("Student Not Found"));
		
		GetStudentDTO response = new GetStudentDTO();
		response.setName(student.getName());
		response.setEmail(student.getEmail());
		response.setCourse(student.getCourse());
		
		return response;
	}
	
	
}
