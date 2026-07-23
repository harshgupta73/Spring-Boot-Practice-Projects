package com.example.crud3JPArelationships3.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.example.crud3JPArelationships3.dtos.req.AddAadharDTO;
import com.example.crud3JPArelationships3.dtos.req.AddStudentDTO;
import com.example.crud3JPArelationships3.dtos.req.UpdateAadharDTO;
import com.example.crud3JPArelationships3.dtos.req.UpdateStudentDTO;
import com.example.crud3JPArelationships3.dtos.res.GetAadharDTO;
import com.example.crud3JPArelationships3.dtos.res.GetStudentDTO;
import com.example.crud3JPArelationships3.entity.Aadhar;
import com.example.crud3JPArelationships3.entity.Student;

@Mapper(componentModel = "spring")
public interface StudentMapper {
	Student toEntity(AddStudentDTO addStudentDTO);
	GetStudentDTO toDTO(Student student);
	List<GetStudentDTO> toDTOList(List<Student> students);
	void updateStudentFromDTO(UpdateStudentDTO dto, @MappingTarget Student student);
	
	Aadhar toEntity(AddAadharDTO aadharDTO);
	GetAadharDTO toDTO(Aadhar aadhar);
	void updateAadharFromDTO(UpdateAadharDTO aadharDTO,@MappingTarget Aadhar aadhar);
}
