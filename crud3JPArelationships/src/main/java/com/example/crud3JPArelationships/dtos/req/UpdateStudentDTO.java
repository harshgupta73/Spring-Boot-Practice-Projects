package com.example.crud3JPArelationships.dtos.req;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStudentDTO {
	
	@NotBlank(message = "name is required")
	private String name;
	
	@NotBlank(message = "email is required")
	@Email(message = "email must be valid")
	private String email;
	
	@NotBlank(message = "course is required")
	private String course;
	
	@NotNull(message = "aadhar details are required")
	@Valid
	private UpdateAadharDTO aadhar;
}
