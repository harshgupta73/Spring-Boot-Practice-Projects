package com.example.crud3PaginationandSorting.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.crud3PaginationandSorting.dtos.req.AddStudentDTO;
import com.example.crud3PaginationandSorting.dtos.req.UpdateStudentDTO;
import com.example.crud3PaginationandSorting.dtos.res.GetStudentDTO;
import com.example.crud3PaginationandSorting.entity.Student;
import com.example.crud3PaginationandSorting.exception.DuplicateResourceException;
import com.example.crud3PaginationandSorting.exception.ResourceNotFoundException;
import com.example.crud3PaginationandSorting.mapper.StudentMapper;
import com.example.crud3PaginationandSorting.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository repository;
	
	@Autowired
	private StudentMapper mapper;
	
	public GetStudentDTO addStudent(AddStudentDTO addStudentDTO) {
		
		if(repository.existsByEmail(addStudentDTO.getEmail())) {
			throw new DuplicateResourceException("Email already exists");
		}
		
		if(repository.existsByAadhar(addStudentDTO.getAadhar())) {
			throw new DuplicateResourceException("Aadhar already exists");
		}
		
		Student savedStudent = repository.save(mapper.toEntity(addStudentDTO));
		return mapper.toDTO(savedStudent);
	}
	
	public GetStudentDTO updateStudent(Long id, UpdateStudentDTO dto) {
		
		Student existing = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Student Not Found with ID:"+id));
		if(!existing.getEmail().equals(dto.getEmail()) && repository.existsByEmail(dto.getEmail())) {
			throw new DuplicateResourceException("Email already exists");
		}
		
		mapper.updateStudentFromDTO(dto, existing);
		repository.save(existing);
		return mapper.toDTO(existing);
	}
	
	public Map<String, String> deleteStudent(Long id) {

		Student existing =repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Student Not found with ID:"+id));
		
		repository.delete(existing);
		
		Map<String, String> response = new HashMap<>();
		response.put("messsage", "Student deleted");
		return response;
	}
	
	public List<GetStudentDTO> getAllStudents() {
		
		return mapper.toDTOList(repository.findAll()); 
		
		//or you can write below lines for the same
		//List<Student> students = repository.findAll();
		//return mapper.toDTOList(students);
	}
	
	public GetStudentDTO getStudentById(Long id) {
		// TODO Auto-generated method stub
		Student existing = repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Student Not Found with ID:"+id));
		return mapper.toDTO(existing);
	}
	
	//http://localhost:8081/students/page?page=0&size=3 since we have both the methods of get all students with same name use this url
	//or comment out one method without pagination and use this url http://localhost:8081/students?page=0&size=3
	public Page<GetStudentDTO> getAllStudents(int page,int size){
		
		Pageable pageable = PageRequest.of(page, size);
		
		Page<Student> students =repository.findAll(pageable);
		 
		return students.map(mapper::toDTO);
	}
	
//	public Page<GetStudentDTO> getAllStudents(int page, int size, String sortBy, String direction){
//		
//		
//		Sort sort = direction.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
//		
//		Pageable pageable = PageRequest.of(page, size, sort);
//		
//		Page<Student> students = repository.findAll(pageable);		
//		return students.map(mapper::toDTO);
//		//instead of the above two lines you can write a single line as shown below
//		//return repository.findAll(pageable).map(mapper::toDTO);
//		
//	}
	
	public Page<GetStudentDTO> getAllStudents(int page, int size, String sortBy, String direction) {

	    if (!direction.equalsIgnoreCase("asc") && !direction.equalsIgnoreCase("desc")) {
	        throw new IllegalArgumentException("Direction must be either 'asc' or 'desc'");
	    }

	    Sort.Direction dir = Sort.Direction.fromString(direction);

	    Pageable pageable = PageRequest.of(page, size, Sort.by(dir, sortBy));

	    return repository.findAll(pageable)
	                     .map(mapper::toDTO);
	}
}
