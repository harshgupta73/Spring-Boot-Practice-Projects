package com.example.crud3JPArelationships3.dtos.req;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStudentDTO {
	
	@NotBlank(message = "name is required")
	@Size(min = 3,max = 50,message = "name must be 30 to 50 characters")
	private String name;
	
	@NotBlank(message = "email is required")
	@Email(message = "Email must be valid")
	private String email;
	
	@NotBlank(message = "course is required")
	private String course;
	
	@NotNull(message = "Aadhar details are required")
	@Valid
	private UpdateAadharDTO aadhar;
	
	@NotNull(message = "department id is required")
	private Long departmentId; 
	
	
}
