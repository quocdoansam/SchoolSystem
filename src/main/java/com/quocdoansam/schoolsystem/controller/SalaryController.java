package com.quocdoansam.schoolsystem.controller;

import java.time.YearMonth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quocdoansam.schoolsystem.dto.request.SalaryCreationRequest;
import com.quocdoansam.schoolsystem.dto.request.SalaryUpdateRequest;
import com.quocdoansam.schoolsystem.dto.response.BaseResponse;
import com.quocdoansam.schoolsystem.dto.response.SalaryResponse;
import com.quocdoansam.schoolsystem.service.SalaryService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api")
public class SalaryController {
    @Autowired
    SalaryService salaryService;

    @PostMapping("/admin/salary")
    public ResponseEntity<BaseResponse<SalaryResponse>> createSalary(
            @RequestBody @Valid SalaryCreationRequest request) {
        SalaryResponse salaryResponse = salaryService.createSalary(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(BaseResponse.<SalaryResponse>builder()
                        .success(true)
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Salary created successfully.")
                        .data(salaryResponse)
                        .build());
    }

    @GetMapping("/salaries")
    public ResponseEntity<BaseResponse<SalaryResponse>> getSalaryByTeacherAndMonth(@RequestParam Long teacherId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM") YearMonth month) {
        SalaryResponse salaryResponse = salaryService.getSalaryByTeacherAndMonth(teacherId, month);
        return ResponseEntity.ok(
                BaseResponse.<SalaryResponse>builder()
                        .success(true)
                        .statusCode(HttpStatus.OK.value())
                        .message("Get Salary successfully.")
                        .data(salaryResponse)
                        .build());
    }

    @PutMapping("salaries/{id}")
    public ResponseEntity<BaseResponse<SalaryResponse>> putMethodName(@PathVariable String id,
            @RequestBody SalaryUpdateRequest request) {
        SalaryResponse salaryResponse = salaryService.updateSalary(id, request);
        return ResponseEntity.ok(
                BaseResponse.<SalaryResponse>builder()
                        .success(true)
                        .statusCode(HttpStatus.OK.value())
                        .message("Salary updated successfully.")
                        .data(salaryResponse)
                        .build());
    }
}
