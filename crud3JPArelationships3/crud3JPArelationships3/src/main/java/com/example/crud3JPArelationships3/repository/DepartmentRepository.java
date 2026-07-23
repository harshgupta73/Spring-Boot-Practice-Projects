package com.example.crud3JPArelationships3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crud3JPArelationships3.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long>{
	boolean existsByName(String name);
}
