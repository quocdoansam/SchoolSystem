package com.quocdoansam.schoolsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quocdoansam.schoolsystem.dto.request.TuitionFeeCreationRequest;
import com.quocdoansam.schoolsystem.dto.response.BaseResponse;
import com.quocdoansam.schoolsystem.dto.response.TuitionFeeResponse;
import com.quocdoansam.schoolsystem.service.TuitionFeeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/public")
public class TuitionFeeController {
    @Autowired
    TuitionFeeService tuitionFeeService;

    @PostMapping("/tuition-fees")
    public ResponseEntity<BaseResponse<TuitionFeeResponse>> create(@RequestBody TuitionFeeCreationRequest request) {
        TuitionFeeResponse tuitionFeeResponse = tuitionFeeService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                BaseResponse.<TuitionFeeResponse>builder()
                        .success(true)
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Tuition fee created successfully.")
                        .data(tuitionFeeResponse)
                        .build());
    }

}
