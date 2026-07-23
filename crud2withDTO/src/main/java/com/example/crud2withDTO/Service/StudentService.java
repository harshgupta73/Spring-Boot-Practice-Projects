package com.example.crud2withDTO.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.crud2withDTO.Crud2withDtoApplication;
import com.example.crud2withDTO.DTO.StudentDTO;
import com.example.crud2withDTO.entity.Student;
import com.example.crud2withDTO.repository.StudentRepository;

@Service
public class StudentService {

    private final Crud2withDtoApplication crud2withDtoApplication;
	
	@Autowired
	private StudentRepository repository;

    StudentService(Crud2withDtoApplication crud2withDtoApplication) {
        this.crud2withDtoApplication = crud2withDtoApplication;
    }
	
	public StudentDTO addStudent(StudentDTO dto) {
		// TODO Auto-generated method stub
		
		Student student = new Student();
		student.setName(dto.getName());
		student.setEmail(dto.getEmail());
		student.setCourse(dto.getCourse());
		
		Student savedStudent = repository.save(student);
		
		StudentDTO response = new StudentDTO();
		response.setName(savedStudent.getName());
		response.setEmail(savedStudent.getEmail());
		response.setCourse(savedStudent.getCourse());
		
		return response;
	}
	
	public StudentDTO updateStudent(Long id, StudentDTO dto) {
		// TODO Auto-generated method stub
		Student existing = repository.findById(id).orElseThrow(() -> new RuntimeException("Student Not Found"));
		existing.setName(dto.getName());
		existing.setEmail(dto.getEmail());
		existing.setCourse(dto.getCourse());
		
		Student updatedStudent =repository.save(existing);
		
		StudentDTO response = new StudentDTO();
		response.setName(updatedStudent.getName());
		response.setEmail(updatedStudent.getEmail());
		response.setCourse(updatedStudent.getCourse());
		
		return response;
		
	}
	
	public String deleteStudent(Long id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
		return "student deleted";
	}
	
	public List<StudentDTO> getAllStudents() {
		// TODO Auto-generated method stub
		List<Student> students = repository.findAll();
		
		List<StudentDTO> studentDTOs = new ArrayList<StudentDTO>();
		
		for(Student student:students) {
			StudentDTO dto = new StudentDTO();
			dto.setName(student.getName());
			dto.setEmail(student.getEmail());
			dto.setCourse(student.getCourse());
			
			studentDTOs.add(dto);
		}
		
		return studentDTOs;
	}
	
	public StudentDTO getStudentById(Long id) {
		// TODO Auto-generated method stub
		Student student = repository.findById(id).orElseThrow(() -> new RuntimeException("Student Not Found"));
		
		StudentDTO response = new StudentDTO();
		response.setName(student.getName());
		response.setEmail(student.getEmail());
		response.setCourse(student.getCourse());
		
		return response;
	}
}
