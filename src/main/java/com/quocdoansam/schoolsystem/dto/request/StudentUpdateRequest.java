package com.quocdoansam.schoolsystem.dto.request;

import java.time.LocalDate;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentUpdateRequest {
    String password;
    String name;
    LocalDate dob;
    String gender;
    String hometown;
    String address;
    String email;
    String phoneNumber;
    String majorId;
    double gpa;
}
