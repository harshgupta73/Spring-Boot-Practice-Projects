package com.example.crud3mapstruct.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.example.crud3mapstruct.dtos.req.AddStudentDTO;
import com.example.crud3mapstruct.dtos.req.UpdateStudentDTO;
import com.example.crud3mapstruct.dtos.res.GetStudentDTO;
import com.example.crud3mapstruct.entity.Student;

@Mapper(componentModel = "spring")
public interface StudentMapper {
	Student toEntity(AddStudentDTO addStudentDTO);
	
	void updateStudentFromDTO(UpdateStudentDTO dto, @MappingTarget Student student);
	
	GetStudentDTO toDTO(Student student);
	
	List<GetStudentDTO> toDTOList(List<Student> students);
}
