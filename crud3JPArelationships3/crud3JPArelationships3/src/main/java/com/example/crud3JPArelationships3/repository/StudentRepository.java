package com.example.crud3JPArelationships3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crud3JPArelationships3.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{
	boolean existsByEmail(String email);
	boolean existsByAadhar_AadharNumber(String aadharNumber);
	boolean existsByDepartment_Id(Long departmentId);
}
