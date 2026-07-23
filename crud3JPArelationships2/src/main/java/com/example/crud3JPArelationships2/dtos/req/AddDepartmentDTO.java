package com.example.crud3JPArelationships2.dtos.req;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddDepartmentDTO {
	
	@NotBlank(message = "Name is required")
	private String name;
	
}
