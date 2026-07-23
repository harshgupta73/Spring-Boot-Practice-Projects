package com.example.crud3JPArelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crud3JPArelationships.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{
	boolean existsByEmail(String email);
	boolean existsByAadhar_AadharNumber(String aadharNumber);
}
