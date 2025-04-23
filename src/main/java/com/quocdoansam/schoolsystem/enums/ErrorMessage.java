package com.quocdoansam.schoolsystem.enums;

import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorMessage {
    INVALID_FULL_NAME(HttpStatus.BAD_REQUEST, "The full name must be at least 2 up to 50 characters."),
    STUDENT_NOT_FOUND(HttpStatus.NOT_FOUND, "The student not found."),
    EMAIL_EXISTED(HttpStatus.BAD_REQUEST, "The email has existed."),

    SUBJECT_NOT_FOUND(HttpStatus.NOT_FOUND, "The subject not found."),
    SUBJECT_NAME_EXISTED(HttpStatus.BAD_REQUEST, "The subject name has been existed."),

    TEACHER_NOT_FOUND(HttpStatus.NOT_FOUND, "The teacher not found."),
    WRONG_CREDENTIALS(HttpStatus.UNAUTHORIZED, "Wrong username or password. Try again."),
    INVALID_ROLE_SPECIFIED(HttpStatus.BAD_REQUEST, "Invalid role specified."),
    UNSUPPORTED_ROLE(HttpStatus.NOT_ACCEPTABLE, "Unsupport role."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "The user not found."),
    MAJOR_NOT_FOUND(HttpStatus.NOT_FOUND, "The major not found."),
    PHONE_NUMBER_EXISTED(HttpStatus.BAD_REQUEST, "The phone number has existed."),
    CANNOT_DELETE(HttpStatus.INTERNAL_SERVER_ERROR, "Found an error during deletion. Please try again."),
    PAYROLL_EXISTED(HttpStatus.BAD_REQUEST, "Salary for this teacher already existed."),
    INVALID_SALARY_STATUS(HttpStatus.BAD_REQUEST,
            "Invalid salary status, accepted values are: PENDING, APPROVED, PAID or REJECTED."),
    SALARY_NOT_FOUND(HttpStatus.NOT_FOUND, "Salary not found.");

    HttpStatus status;
    String message;
}