package com.quocdoansam.schoolsystem.controller;

import com.quocdoansam.schoolsystem.dto.request.StudentCreationRequest;
import com.quocdoansam.schoolsystem.dto.response.BaseResponse;
import com.quocdoansam.schoolsystem.dto.response.StudentResponse;
import com.quocdoansam.schoolsystem.service.StudentService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@Slf4j
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/admin/students")
    ResponseEntity<BaseResponse<StudentResponse>> create(@RequestBody @Valid StudentCreationRequest request) {
        StudentResponse studentResponse = studentService.create(request);
        return ResponseEntity.status(
                HttpStatus.CREATED).body(
                        BaseResponse.<StudentResponse>builder()
                                .success(true)
                                .statusCode(HttpStatus.CREATED.value())
                                .message("Student created successfully.")
                                .data(studentResponse)
                                .build());

    };

    @GetMapping("/admin/students")
    public ResponseEntity<BaseResponse<List<StudentResponse>>> getAll() {
        List<StudentResponse> studentResponses = studentService.getAll();
        return ResponseEntity.ok(
                BaseResponse.<List<StudentResponse>>builder()
                        .success(true)
                        .statusCode(HttpStatus.OK.value())
                        .message("Get all students successfully.")
                        .data(studentResponses)
                        .build());
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<BaseResponse<StudentResponse>> getById(@PathVariable Long id) {
        StudentResponse studentResponse = studentService.getById(id);
        return ResponseEntity.ok(
                BaseResponse.<StudentResponse>builder()
                        .success(true)
                        .statusCode(HttpStatus.OK.value())
                        .message("Get student successfully.")
                        .data(studentResponse)
                        .build());
    }

}
