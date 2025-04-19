package com.quocdoansam.schoolsystem.service;

import java.util.HashSet;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.quocdoansam.schoolsystem.dto.request.StudentCreationRequest;
import com.quocdoansam.schoolsystem.dto.response.StudentResponse;
import com.quocdoansam.schoolsystem.entity.Major;
import com.quocdoansam.schoolsystem.entity.Student;
import com.quocdoansam.schoolsystem.enums.Role;
import com.quocdoansam.schoolsystem.mapper.StudentMapper;
import com.quocdoansam.schoolsystem.repository.MajorRepository;
import com.quocdoansam.schoolsystem.repository.StudentRepository;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class StudentService {
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	StudentMapper studentMapper;
	@Autowired
	MajorRepository majorRepository;
	@Autowired
	PasswordEncoder passwordEncoder;

	public StudentResponse create(StudentCreationRequest request) {
		Student student = studentMapper.toStudentCreationRequest(request);

		// Add major
		Major major = majorRepository.findById(request.getMajorId())
				.orElseThrow(() -> new RuntimeException("Major not found with id = " + request.getMajorId()));
		student.setMajor(major);

		// Add roles
		Set<String> roles = new HashSet<>();
		roles.add(Role.STUDENT.name());
		student.setRoles(roles);

		// Password cryption
		student.setPassword(passwordEncoder.encode(student.getPassword()));

		return studentMapper.toStudentResponse(studentRepository.save(student));
	}

}
