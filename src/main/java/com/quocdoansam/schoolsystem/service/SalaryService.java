package com.quocdoansam.schoolsystem.service;

import java.time.YearMonth;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quocdoansam.schoolsystem.dto.request.SalaryCreationRequest;
import com.quocdoansam.schoolsystem.dto.request.SalaryUpdateRequest;
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

    public SalaryResponse getSalaryById(String id) {
        Salary salary = findById(id);
        return salaryMapper.toSalaryResponse(salary);
    }

    public Salary findById(String id) {
        return salaryRepository.findById(id)
                .orElseThrow(() -> new BaseException(ErrorMessage.SALARY_NOT_FOUND));
    }

    public List<SalaryResponse> getSalaryByTeacherId(Long id) {
        Teacher teacher = teacherService.findById(id);
        return salaryRepository.findByTeacher(teacher)
                .stream()
                .map(salaryMapper::toSalaryResponse)
                .toList();
    }

    public SalaryResponse getSalaryByTeacherAndMonth(Long id, YearMonth yearMonth) {
        Teacher teacher = teacherService.findById(id);
        Salary salary = salaryRepository.findByMonthSalaryAndTeacher(yearMonth, teacher);
        return salaryMapper.toSalaryResponse(salary);
    }

    public List<SalaryResponse> getSalaryByMonth(YearMonth yearMonth) {
        // if the year month is null, get salary by current month.
        if (yearMonth == null) {
            YearMonth currentMonth = YearMonth.now();
            return salaryRepository.findByMonthSalary(currentMonth)
                    .stream()
                    .map(salaryMapper::toSalaryResponse)
                    .toList();
        }
        return salaryRepository.findByMonthSalary(yearMonth)
                .stream()
                .map(salaryMapper::toSalaryResponse)
                .toList();
    }

    public SalaryResponse updateSalary(String id, SalaryUpdateRequest request) {
        // Get teacher
        Teacher teacher = teacherService.findById(request.getTeacherId());

        // Get and map salary data
        Salary salary = findById(id);
        salary = salaryMapper.toSalaryUpdateRequest(request, salary);

        // Set teacher
        salary.setTeacher(teacher);

        // Return and save salary
        return salaryMapper.toSalaryResponse(salaryRepository.save(salary));
    }
}
