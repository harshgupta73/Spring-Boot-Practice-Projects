package com.example.crud3JPArelationships3.dtos.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddAadharDTO {
	
	@NotBlank(message = "aadhar number is required")
	@Pattern(
		regexp = "\\d{12}",
		message = "Aadhar must be exactly 12 digits"
	)
	private String aadharNumber;
	
	@NotBlank(message = "Address is required")
	private String address;
	
	@NotBlank(message = "city is required")
	private String city;
	
	@NotBlank(message = "state is required")
	private String state;
	
	
}
