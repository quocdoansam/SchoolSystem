package com.quocdoansam.schoolsystem.repository;

import java.time.YearMonth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quocdoansam.schoolsystem.entity.Salary;
import com.quocdoansam.schoolsystem.entity.Teacher;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, String> {
    public boolean existsByTeacherAndMonthSalary(Teacher teacher, YearMonth yearMonth);
}
