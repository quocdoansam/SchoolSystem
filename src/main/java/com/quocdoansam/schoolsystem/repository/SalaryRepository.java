package com.quocdoansam.schoolsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quocdoansam.schoolsystem.entity.Salary;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, String> {

}
