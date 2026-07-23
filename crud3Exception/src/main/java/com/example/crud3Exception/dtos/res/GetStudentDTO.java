package com.example.crud3Exception.dtos.res;

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
public class GetStudentDTO {
	
	@NotBlank(message = "name is required")
	@Size(min = 3, max = 50, message = "name must be 3 to 50 characters")
	private String name;
	
	@NotBlank(message = "email is required")
	@Email(message = "Email is not valid")
	private String email;
	
	@NotBlank(message = "Course is required")
	private String course;
}
