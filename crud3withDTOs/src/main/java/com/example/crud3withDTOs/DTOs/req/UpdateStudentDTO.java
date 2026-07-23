package com.example.crud3withDTOs.DTOs.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStudentDTO {
	private String name;
	private String email;
	private String course;
}
