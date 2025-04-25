package com.quocdoansam.schoolsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quocdoansam.schoolsystem.entity.User;
import com.quocdoansam.schoolsystem.enums.ErrorMessage;
import com.quocdoansam.schoolsystem.exception.BaseException;
import com.quocdoansam.schoolsystem.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    void checkEmail(String email) {
        if (email != null && userRepository.existsByEmail(email)) {
            throw new BaseException(ErrorMessage.EMAIL_EXISTED);
        }
    }

    void checkPhoneNumber(String phoneNumber) {
        if (phoneNumber != null && userRepository.existsByPhoneNumber(phoneNumber)) {
            throw new BaseException(ErrorMessage.PHONE_NUMBER_EXISTED);
        }
    }

    User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new BaseException(ErrorMessage.USER_NOT_FOUND));
    }
}
