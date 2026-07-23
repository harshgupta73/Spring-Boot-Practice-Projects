package com.example.crud3mapstruct.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crud3mapstruct.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{
	boolean existsByEmail(String email);
	boolean existsByAadhar(String aadhar);
}
