package com.example.crud3JPArelationships2.dtos.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddAadharDTO {
	
	@NotBlank(message = "Aadhar number is required")
	@Pattern(
			regexp = "\\d{12}",
			message = "Aadhar number must be 12 digits long"
	)
	private String aadharNumber;
	
	@NotBlank(message = "address is required")
	private String address;
	
	
	@NotBlank(message = "city is required")
	private String city;
	
	@NotBlank(message = "state is required")
	private String state;
}
