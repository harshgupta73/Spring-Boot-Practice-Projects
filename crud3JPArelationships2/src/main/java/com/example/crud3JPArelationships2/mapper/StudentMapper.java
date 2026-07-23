package com.example.crud3JPArelationships2.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.example.crud3JPArelationships2.dtos.req.AddAadharDTO;
import com.example.crud3JPArelationships2.dtos.req.AddStudentDTO;
import com.example.crud3JPArelationships2.dtos.req.UpdateAadharDTO;
import com.example.crud3JPArelationships2.dtos.req.UpdateStudentDTO;
import com.example.crud3JPArelationships2.dtos.res.GetAadharDTO;
import com.example.crud3JPArelationships2.dtos.res.GetStudentDTO;
import com.example.crud3JPArelationships2.entity.Aadhar;
import com.example.crud3JPArelationships2.entity.Student;

@Mapper(componentModel = "spring")
public interface StudentMapper {
	
	Student toEntity(AddStudentDTO addStudentDTO);
	void updateStudentFromDTO(UpdateStudentDTO dto,@MappingTarget Student student);
	GetStudentDTO toDTO(Student student);
	List<GetStudentDTO> toDTOList(List<Student> students);
	
	Aadhar toEntity(AddAadharDTO aadharDTO);
	void updateAadharFromDTO(UpdateAadharDTO aadharDTO,@MappingTarget Aadhar aadhar);
	GetAadharDTO toDTO(Aadhar aadhar);
}
