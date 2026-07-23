package com.example.crud3JPArelationships.dtos.req;

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
public class AddStudentDTO {
	
	@NotBlank(message = "name is required")
	@Size(min = 3,max = 50,message = "Name must be 3 to 50 characters long")
	private String name;
	
	@NotBlank(message = "Email is required")
	@Email(message = "Email must be valid")
	private String email;
	
	@NotBlank(message = "course is required")
	private String course;
	
	@NotNull(message = "aadhar details are required")
	@Valid //nested bean validation
	private AddAadharDTO aadhar;
}
