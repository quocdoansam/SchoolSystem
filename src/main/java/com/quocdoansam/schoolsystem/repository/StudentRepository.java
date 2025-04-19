package com.quocdoansam.schoolsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quocdoansam.schoolsystem.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	Boolean existsByEmail(String email);
}
