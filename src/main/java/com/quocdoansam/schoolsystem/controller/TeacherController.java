package com.quocdoansam.schoolsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quocdoansam.schoolsystem.dto.request.TeacherCreationRequest;
import com.quocdoansam.schoolsystem.dto.request.TeacherUpdateRequest;
import com.quocdoansam.schoolsystem.dto.response.BaseResponse;
import com.quocdoansam.schoolsystem.dto.response.TeacherResponse;
import com.quocdoansam.schoolsystem.service.TeacherService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api")
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    @PostMapping("/admin/teachers")
    public ResponseEntity<BaseResponse<TeacherResponse>> create(@RequestBody TeacherCreationRequest request) {
        TeacherResponse teacherResponse = teacherService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                BaseResponse.<TeacherResponse>builder()
                        .success(true)
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Teacher created successfully.")
                        .data(teacherResponse)
                        .build());
    }

    @GetMapping("/teachers/{id}")
    public ResponseEntity<BaseResponse<TeacherResponse>> getById(@PathVariable Long id) {

        TeacherResponse teacherResponse = teacherService.getById(id);
        return ResponseEntity.ok(
                BaseResponse.<TeacherResponse>builder()
                        .success(true)
                        .statusCode(HttpStatus.OK.value())
                        .message("Get teacher successfully.")
                        .data(teacherResponse)
                        .build());
    }

    @GetMapping("/admin/teachers")
    public ResponseEntity<BaseResponse<List<TeacherResponse>>> getAllTeacher() {
        List<TeacherResponse> teacherResponses = teacherService.getAll();
        return ResponseEntity.ok(
                BaseResponse.<List<TeacherResponse>>builder()
                        .success(true)
                        .statusCode(HttpStatus.OK.value())
                        .message("Get all teachers successfully.")
                        .data(teacherResponses)
                        .build());
    }

    @PutMapping("/teachers/{id}")
    public ResponseEntity<BaseResponse<TeacherResponse>> update(@PathVariable Long id,
            @RequestBody TeacherUpdateRequest request) {
        TeacherResponse teacherResponse = teacherService.updateById(id, request);

        return ResponseEntity.ok(
                BaseResponse.<TeacherResponse>builder()
                        .success(true)
                        .statusCode(HttpStatus.OK.value())
                        .message("Teacher updated successfully.")
                        .data(teacherResponse)
                        .build());
    }

    @DeleteMapping("/teachers/{id}")
    public ResponseEntity<BaseResponse<?>> deleteTeacherById(@PathVariable Long id) {
        teacherService.deleteById(id);
        return ResponseEntity.ok(
                BaseResponse.builder()
                        .success(true)
                        .statusCode(HttpStatus.OK.value())
                        .message("Teacher deleted successfully.")
                        .build());
    }

    @DeleteMapping("/admin/teachers")
    public ResponseEntity<BaseResponse<?>> deleteAllTeacher() {
        teacherService.deleteAll();
        return ResponseEntity.ok(
                BaseResponse.builder()
                        .success(true)
                        .statusCode(HttpStatus.OK.value())
                        .message("All teachers deleted successfully.")
                        .build());
    }
}
