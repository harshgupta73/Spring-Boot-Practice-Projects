package com.example.crud3JPArelationships3.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.example.crud3JPArelationships3.dtos.req.AddDepartmentDTO;
import com.example.crud3JPArelationships3.dtos.req.UpdateDepartmentDTO;
import com.example.crud3JPArelationships3.dtos.res.GetDepartmentDTO;
import com.example.crud3JPArelationships3.entity.Department;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
	Department toEntity(AddDepartmentDTO addDepartmentDTO);
	
	GetDepartmentDTO toDTO(Department department);
	
	List<GetDepartmentDTO> toDTOList(List<Department> departments);
	
	void updateDepartmentFromDTO(UpdateDepartmentDTO departmentDTO, @MappingTarget Department department);
}
