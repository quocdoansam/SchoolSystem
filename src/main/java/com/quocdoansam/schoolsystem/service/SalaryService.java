package com.quocdoansam.schoolsystem.service;

import java.time.YearMonth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quocdoansam.schoolsystem.dto.request.SalaryCreationRequest;
import com.quocdoansam.schoolsystem.dto.response.SalaryResponse;
import com.quocdoansam.schoolsystem.entity.Salary;
import com.quocdoansam.schoolsystem.entity.Teacher;
import com.quocdoansam.schoolsystem.enums.ErrorMessage;
import com.quocdoansam.schoolsystem.exception.BaseException;
import com.quocdoansam.schoolsystem.mapper.SalaryMapper;
import com.quocdoansam.schoolsystem.repository.SalaryRepository;

@Service
public class SalaryService {
    @Autowired
    SalaryMapper salaryMapper;
    @Autowired
    SalaryRepository salaryRepository;
    @Autowired
    TeacherService teacherService;

    public SalaryResponse createSalary(SalaryCreationRequest request) {
        // Get current month of year
        YearMonth currMonth = YearMonth.now();
        // Get teacher
        Teacher teacher = teacherService.findById(request.getTeacherId());

        // salary month Validation
        if (salaryRepository.existsByTeacherAndMonthSalary(teacher, currMonth)) {
            throw new BaseException(ErrorMessage.PAYROLL_EXISTED);
        }

        // Map data
        Salary salary = salaryMapper.toSalaryCreationRequest(request);

        // Set teacher
        salary.setTeacher(teacher);

        // Set salary month
        salary.setMonthSalary(YearMonth.now());

        return salaryMapper.toSalaryResponse(salaryRepository.save(salary));
    }
}
