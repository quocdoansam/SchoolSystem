package com.quocdoansam.schoolsystem.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.quocdoansam.schoolsystem.dto.request.TeacherCreationRequest;
import com.quocdoansam.schoolsystem.dto.response.TeacherResponse;
import com.quocdoansam.schoolsystem.entity.Teacher;
import com.quocdoansam.schoolsystem.enums.ErrorMessage;
import com.quocdoansam.schoolsystem.enums.Role;
import com.quocdoansam.schoolsystem.exception.BaseException;
import com.quocdoansam.schoolsystem.mapper.TeacherMapper;
import com.quocdoansam.schoolsystem.repository.TeacherRepository;

@Service
public class TeacherService {
    @Autowired
    TeacherMapper teacherMapper;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public TeacherResponse create(TeacherCreationRequest request) {
        // Validation
        userService.checkEmail(request.getEmail());
        userService.checkPhoneNumber(request.getPhoneNumber());

        // Mapping request
        Teacher teacher = teacherMapper.toTeacherCreationRequest(request);

        // Add roles
        teacher.setRoles(Set.of(Role.TEACHER.name()));

        // Password cryption
        teacher.setPassword(passwordEncoder.encode(request.getPassword()));

        // Return and save teacher into database
        return teacherMapper.toTeacherResponse(teacherRepository.save(teacher));
    }

    public TeacherResponse getById(Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new BaseException(ErrorMessage.TEACHER_NOT_FOUND));
        return teacherMapper.toTeacherResponse(teacher);
    }
}
