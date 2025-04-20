package com.quocdoansam.schoolsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quocdoansam.schoolsystem.dto.request.MajorCreationRequest;
import com.quocdoansam.schoolsystem.dto.response.BaseResponse;
import com.quocdoansam.schoolsystem.dto.response.MajorResponse;
import com.quocdoansam.schoolsystem.service.MajorService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api")
public class MajorController {
    @Autowired
    MajorService majorService;

    @PostMapping("/admin/majors")
    public ResponseEntity<BaseResponse<MajorResponse>> create(@RequestBody MajorCreationRequest request) {
        MajorResponse majorResponse = majorService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                BaseResponse.<MajorResponse>builder()
                        .success(true)
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Major created successfully.")
                        .data(majorResponse)
                        .build());
    }

    @GetMapping("/public/majors/{id}")
    public ResponseEntity<BaseResponse<MajorResponse>> getMajorById(@PathVariable String id) {
        MajorResponse tuitionFees = majorService.getMajorById(id);
        return ResponseEntity.ok(
                BaseResponse.<MajorResponse>builder()
                        .success(true)
                        .statusCode(HttpStatus.OK.value())
                        .message("Get major successfully.")
                        .data(tuitionFees)
                        .build());
    }

}
