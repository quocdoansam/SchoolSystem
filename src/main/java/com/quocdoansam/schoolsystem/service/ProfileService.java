package com.quocdoansam.schoolsystem.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.quocdoansam.schoolsystem.config.UserPrincipal;
import com.quocdoansam.schoolsystem.entity.Major;
import com.quocdoansam.schoolsystem.entity.Student;
import com.quocdoansam.schoolsystem.entity.Teacher;
import com.quocdoansam.schoolsystem.mapper.StudentMapper;
import com.quocdoansam.schoolsystem.mapper.TeacherMapper;
import com.quocdoansam.schoolsystem.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProfileService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    StudentService studentService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    MajorService majorService;
    @Autowired
    TeacherMapper teacherMapper;

    public Object me() {
        UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = user.getId();
        Set<String> roles = user.getRoles();

        if (roles.contains("STUDENT")) {
            Student student = studentService.findById(userId);
            Major major = majorService.findById(student.getMajor().getId());
            student.setMajor(major);

            return studentMapper.toStudentResponse(student);
        } else if (roles.contains("TEACHER")) {
            Teacher teacher = teacherService.findById(userId);
            return teacherMapper.toTeacherResponse(teacher);
        } else {
            return userRepository.findById(userId);
        }
    }
}
