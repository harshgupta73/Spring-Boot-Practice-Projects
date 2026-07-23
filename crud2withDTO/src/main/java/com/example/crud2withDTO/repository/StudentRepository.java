package com.example.crud2withDTO.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crud2withDTO.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

}
