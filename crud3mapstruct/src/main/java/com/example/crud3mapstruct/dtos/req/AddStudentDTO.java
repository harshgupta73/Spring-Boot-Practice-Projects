package com.example.crud3mapstruct.dtos.req;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddStudentDTO {
	
	@NotBlank(message = "name is required")
	@Size(min = 3,max = 50,message = "name must be 3 to 50 characters long")
	private String name;
	
	@NotBlank(message = "email is required")
	@Email(message = "Email is not valid")
	private String email;
	
	@NotBlank(message = "course is required")
	private String course;
	
	@Pattern(
			regexp = "\\d{12}",
			message = "Aadhar must be 12 digits long"
	)
	private String aadhar;
}
