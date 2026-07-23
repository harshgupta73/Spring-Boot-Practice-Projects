package com.example.crud3withDTOs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crud3withDTOs.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

}
