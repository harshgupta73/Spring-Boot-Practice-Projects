package com.example.crud3JPArelationships.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.example.crud3JPArelationships.dtos.req.AddAadharDTO;
import com.example.crud3JPArelationships.dtos.req.AddStudentDTO;
import com.example.crud3JPArelationships.dtos.req.UpdateAadharDTO;
import com.example.crud3JPArelationships.dtos.req.UpdateStudentDTO;
import com.example.crud3JPArelationships.dtos.res.GetAadharDTO;
import com.example.crud3JPArelationships.dtos.res.GetStudentDTO;
import com.example.crud3JPArelationships.entity.Aadhar;
import com.example.crud3JPArelationships.entity.Student;

@Mapper(componentModel = "spring")
public interface StudentMapper {
	
	Student toEntity(AddStudentDTO addStudentDTO);
	
	void updateStudentFromDTO(UpdateStudentDTO dto, @MappingTarget Student student);
	
	GetStudentDTO toDTO(Student student);
	
	List<GetStudentDTO> toDTOList(List<Student> students);
	
	// Nested mappings
    Aadhar toEntity(AddAadharDTO dto);
    
    //@Mapping(target = "city", ignore = true) //if you want to never update city use this line
    void updateAadharFromDTO(UpdateAadharDTO aadharDTO, @MappingTarget Aadhar aadhar);
    
    GetAadharDTO toDTO(Aadhar aadhar);
    
    
}
