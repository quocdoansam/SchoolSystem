package com.quocdoansam.schoolsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quocdoansam.schoolsystem.dto.request.SalaryCreationRequest;
import com.quocdoansam.schoolsystem.dto.response.BaseResponse;
import com.quocdoansam.schoolsystem.dto.response.SalaryResponse;
import com.quocdoansam.schoolsystem.service.SalaryService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

}
