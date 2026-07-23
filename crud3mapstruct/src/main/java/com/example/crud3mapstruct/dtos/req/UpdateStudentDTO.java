package com.example.crud3mapstruct.dtos.req;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStudentDTO {
	
	@NotBlank(message = "name is required")
	@Size(min = 3,max = 50,message = "name must be 3 to 50 characters long")
	private String name;
	
	@NotBlank(message = "email is required")
	@Email(message = "email must be valid")
	private String email;
	
	@NotBlank(message = "course is required")
	private String course;
}
