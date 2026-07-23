package com.example.crud3JPArelationships2.dtos.req;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDepartmentDTO {
	
	@NotBlank(message = "name is required")
	private String name;
}
