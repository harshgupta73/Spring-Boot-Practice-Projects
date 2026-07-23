package com.example.crud3withDTOs.DTOs.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddStudentDTO {
	
	private String name;
	private String email;
	private String course;
	private String aadhar;
	
}
