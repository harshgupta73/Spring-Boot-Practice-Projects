package com.example.crud3JPArelationships3.dtos.req;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddDepartmentDTO {
	@NotBlank(message = "department name cannot be blank")
	private String name;
}
