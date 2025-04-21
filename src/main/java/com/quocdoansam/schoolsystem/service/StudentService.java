package com.quocdoansam.schoolsystem.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.quocdoansam.schoolsystem.dto.request.StudentCreationRequest;
import com.quocdoansam.schoolsystem.dto.request.TuitionFeeCreationRequest;
import com.quocdoansam.schoolsystem.dto.response.StudentResponse;
import com.quocdoansam.schoolsystem.entity.Major;
import com.quocdoansam.schoolsystem.entity.Student;
import com.quocdoansam.schoolsystem.enums.ErrorMessage;
import com.quocdoansam.schoolsystem.enums.Role;
import com.quocdoansam.schoolsystem.exception.BaseException;
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
	@Autowired
	TuitionFeeService tuitionFeeService;
	@Autowired
	UserService userService;

	public StudentResponse create(StudentCreationRequest request) {

		// Validation
		userService.checkEmail(request.getEmail());
		userService.checkPhoneNumber(request.getPhoneNumber());

		Student student = studentMapper.toStudentCreationRequest(request);

		// Add major
		Major major = findMajorById(request.getMajorId());
		student.setMajor(major);

		// Add roles
		student.setRoles(Set.of(Role.STUDENT.name()));

		// Password cryption
		student.setPassword(passwordEncoder.encode(student.getPassword()));

		// Save student
		Student savedStudent = studentRepository.save(student);

		// Add tuition fees
		createTuitionFeeForStudent(savedStudent, major.getTuitionFees());

		return studentMapper.toStudentResponse(savedStudent);
	}

	private Major findMajorById(String majorId) {
		return majorRepository.findById(majorId)
				.orElseThrow(() -> new BaseException(ErrorMessage.MAJOR_NOT_FOUND));
	}

	private void createTuitionFeeForStudent(Student student, BigDecimal amount) {
		log.info("STUDENT ID: {}", student.getId());
		TuitionFeeCreationRequest feeRequest = TuitionFeeCreationRequest.builder()
				.studentId(student.getId())
				.amount(amount)
				.paid(false)
				.build();
		tuitionFeeService.create(feeRequest);
	}

	public StudentResponse getById(Long id) {
		return studentMapper.toStudentResponse(
				studentRepository.findById(id)
						.orElseThrow(() -> new BaseException(ErrorMessage.STUDENT_NOT_FOUND)));
	}

	public List<StudentResponse> getAll() {
		return studentRepository.findAll()
				.stream()
				.map(studentMapper::toStudentResponse)
				.toList();
	}
}
