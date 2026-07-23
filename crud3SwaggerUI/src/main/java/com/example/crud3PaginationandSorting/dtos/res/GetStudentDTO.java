package com.example.crud3PaginationandSorting.dtos.res;

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
}
