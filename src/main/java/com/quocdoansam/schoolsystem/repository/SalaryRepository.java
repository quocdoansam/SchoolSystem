package com.quocdoansam.schoolsystem.repository;

import java.time.YearMonth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quocdoansam.schoolsystem.entity.Salary;
import com.quocdoansam.schoolsystem.entity.Teacher;
import java.util.List;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, String> {
    public boolean existsByTeacherAndMonthSalary(Teacher teacher, YearMonth yearMonth);

    public List<Salary> findByTeacher(Teacher teacher);

    public List<Salary> findByMonthSalary(YearMonth monthSalary);

    public Salary findByMonthSalaryAndTeacher(YearMonth monthSalary, Teacher teacher);
}
