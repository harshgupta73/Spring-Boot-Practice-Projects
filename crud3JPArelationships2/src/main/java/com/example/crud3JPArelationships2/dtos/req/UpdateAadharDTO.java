package com.example.crud3JPArelationships2.dtos.req;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAadharDTO {
	
	@NotBlank(message = "address is required")
	private String address;
	
	@NotBlank(message = "city is required")
	private String city;
	
	@NotBlank(message = "state is required")
	private String state;
}
