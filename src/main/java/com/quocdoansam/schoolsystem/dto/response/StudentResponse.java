package com.quocdoansam.schoolsystem.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

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
public class StudentResponse {
	Long id;
	String name;
	LocalDate dob;
	String gender;
	String hometown;
	String address;
	String phoneNumber;
	String email;
	String majorName;
	Double gpa;
	Set<String> roles;
	LocalDateTime createdAt;
}
