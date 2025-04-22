package com.quocdoansam.schoolsystem.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.quocdoansam.schoolsystem.dto.request.StudentCreationRequest;
import com.quocdoansam.schoolsystem.dto.request.StudentUpdateRequest;
import com.quocdoansam.schoolsystem.dto.request.TuitionFeeCreationRequest;
import com.quocdoansam.schoolsystem.dto.response.StudentResponse;
import com.quocdoansam.schoolsystem.entity.Major;
import com.quocdoansam.schoolsystem.entity.Student;
import com.quocdoansam.schoolsystem.enums.ErrorMessage;
import com.quocdoansam.schoolsystem.enums.Role;
import com.quocdoansam.schoolsystem.exception.BaseException;
import com.quocdoansam.schoolsystem.mapper.StudentMapper;
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
	MajorService majorService;
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
		Major major = majorService.findById(request.getMajorId());
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

	private void createTuitionFeeForStudent(Student student, BigDecimal amount) {
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

	public Student findById(Long id) {
		return studentRepository.findById(id)
				.orElseThrow(() -> new BaseException(ErrorMessage.STUDENT_NOT_FOUND));
	}

	public StudentResponse update(Long id, StudentUpdateRequest request) {
		// Validation
		userService.checkEmail(request.getEmail());
		userService.checkPhoneNumber(request.getPhoneNumber());

		// Get student
		Student student = findById(id);
		student = studentMapper.toStudentUpdateRequest(request, student);

		// Get and set major
		Major major = majorService.findById(request.getMajorId());
		student.setMajor(major);

		// Return and save student
		return studentMapper.toStudentResponse(studentRepository.save(student));
	}

	public void deleteById(Long id) {
		try {
			Student student = findById(id);
			studentRepository.deleteById(student.getId());
		} catch (RuntimeException exception) {
			throw new BaseException(ErrorMessage.CANNOT_DELETE);
		}
	}

	public void deleteAll() {
		try {
			studentRepository.deleteAll();
		} catch (RuntimeException exception) {
			throw new BaseException(ErrorMessage.CANNOT_DELETE);
		}
	}
}
