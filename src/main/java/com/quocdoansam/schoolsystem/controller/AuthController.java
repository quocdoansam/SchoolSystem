package com.quocdoansam.schoolsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quocdoansam.schoolsystem.dto.request.AuthRequest;
import com.quocdoansam.schoolsystem.dto.response.AuthResponse;
import com.quocdoansam.schoolsystem.dto.response.BaseResponse;
import com.quocdoansam.schoolsystem.service.AuthService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/auth/token")
    public ResponseEntity<BaseResponse<AuthResponse>> token(@RequestBody AuthRequest request,
            HttpServletResponse response) {
        AuthResponse authResponse = authService.authenticate(request);

        Cookie cookie = new Cookie("access_token", authResponse.getToken());
        cookie.setHttpOnly(false); // Important: Not allowed JavaScript to read
        cookie.setSecure(true); // Only send cookie through HTTPS (Enable while production)
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24 * 30); // 30 days
        cookie.setAttribute("SameSite", "None"); // Allowed website send cookie cross-origin
        response.addCookie(cookie);

        response.addCookie(cookie);

        return ResponseEntity.ok(
                BaseResponse.<AuthResponse>builder()
                        .success(true)
                        .statusCode(HttpStatus.OK.value())
                        .message("Authorized successfully.")
                        .data(authResponse)
                        .build());
    }

    @PostMapping("/auth/logout")
    public ResponseEntity<BaseResponse<?>> logout(HttpServletResponse response) {
        authService.logout(response);
        return ResponseEntity.ok(
                BaseResponse.builder()
                        .success(true)
                        .statusCode(HttpStatus.OK.value())
                        .message("Logout successfully.")
                        .build());
    }

}
