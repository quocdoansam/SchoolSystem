package com.quocdoansam.schoolsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quocdoansam.schoolsystem.dto.response.BaseResponse;
import com.quocdoansam.schoolsystem.service.ProfileService;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api")
public class ProfileController {
    @Autowired
    ProfileService profileService;

    @GetMapping("/me")
    public ResponseEntity<BaseResponse<Object>> me() {
        var me = profileService.me();
        return ResponseEntity.ok(
                BaseResponse.builder()
                        .success(true)
                        .statusCode(HttpStatus.OK.value())
                        .message("Fetch my information successfully.")
                        .data(me)
                        .build()

        );
    }
}
