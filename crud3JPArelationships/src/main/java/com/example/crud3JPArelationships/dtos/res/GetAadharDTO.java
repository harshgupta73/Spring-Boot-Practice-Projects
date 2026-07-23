package com.example.crud3JPArelationships.dtos.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAadharDTO {
	
	private String address;
	
	private String city;
	
	private String state;
	
	//private String maskedAadharNumber;
}
