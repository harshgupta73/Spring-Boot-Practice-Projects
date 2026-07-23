package com.example.crud3Exception.dtos.req;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateStudentDTO {
	
	@NotBlank(message = "Name is required")
	@Size(min = 3, max = 50, message = "name must be 3 to 50 characters")
	private String name;
	
	@NotBlank(message = "Email is required")
	@Email(message = "Email is not Valid")
	private String email;
	
	@NotBlank(message = "Course is required")
	private String course;
}
