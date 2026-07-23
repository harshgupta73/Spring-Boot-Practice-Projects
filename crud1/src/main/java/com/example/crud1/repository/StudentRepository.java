package com.example.crud1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crud1.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

}
