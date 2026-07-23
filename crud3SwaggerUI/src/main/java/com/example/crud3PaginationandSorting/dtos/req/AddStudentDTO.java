package com.example.crud3PaginationandSorting.dtos.req;

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
	
	@NotBlank(message = "Name is required")
	@Size(min = 3,max = 50,message = "Name must be 3 to 50 characters long")
	private String name;
	
	@NotBlank(message = "Email is required")
	@Email(message = "Email must be valid")
	private String email;
	
	@NotBlank(message = "Course is required")
	private String course;
	
	@Pattern(
			regexp = "\\d{12}",
			message = "Aadhar must be 12 digits long"
	)
	private String aadhar;
}
