package com.example.crud3JPArelationships.dtos.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetStudentDTO {
	
	
	private String name;
	private String email;
	private String course;
	private GetAadharDTO aadhar;
}
