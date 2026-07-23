package com.example.crud3withDTOs.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "students3dtos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "name connot be empty")
	@Size(min = 3, max = 50, message = "name must be betweeen 3 and 50 characters")
	private String name;
	
	@NotBlank(message = "email cannot be empty")
	@Email(message = "enter a valid email")
	private String email;
	
	@NotBlank(message = "course cannot be empty")
	private String course;
	
	//@NotNull(message = "Aadhar number is required")
	//@Digits(integer = 12, fraction = 0, message = "Adhar must contain exactly 12 digits")
	@Pattern(
            regexp = "\\d{12}",
            message = "Aadhaar must be exactly 12 digits"
    )
	private String aadhar;
	
}
