package com.quocdoansam.schoolsystem.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.quocdoansam.schoolsystem.config.UserPrincipal;
import com.quocdoansam.schoolsystem.repository.UserRepository;

@Service
public class ProfileService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    StudentService studentService;
    @Autowired
    TeacherService teacherService;

    public Object me() {
        UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = user.getId();
        Set<String> roles = user.getRoles();

        if (roles.contains("STUDENT")) {
            return studentService.findById(userId);
        } else if (roles.contains("TEACHER")) {
            return teacherService.findById(userId);
        } else {
            return userRepository.findById(userId);
        }
    }
}
