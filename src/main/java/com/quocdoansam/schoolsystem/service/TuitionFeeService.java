package com.quocdoansam.schoolsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quocdoansam.schoolsystem.dto.request.TuitionFeeCreationRequest;
import com.quocdoansam.schoolsystem.dto.response.TuitionFeeResponse;
import com.quocdoansam.schoolsystem.entity.Student;
import com.quocdoansam.schoolsystem.entity.TuitionFee;
import com.quocdoansam.schoolsystem.enums.ErrorMessage;
import com.quocdoansam.schoolsystem.exception.BaseException;
import com.quocdoansam.schoolsystem.mapper.TuitionFeeMapper;
import com.quocdoansam.schoolsystem.repository.StudentRepository;
import com.quocdoansam.schoolsystem.repository.TuitionFeeRepository;

@Service
public class TuitionFeeService {

    @Autowired
    TuitionFeeRepository tuitionFeeRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    TuitionFeeMapper tuitionFeeMapper;

    TuitionFeeService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public TuitionFeeResponse create(TuitionFeeCreationRequest request) {
        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new BaseException(ErrorMessage.STUDENT_NOT_FOUND));

        TuitionFee fee = TuitionFee.builder()
                .student(student)
                .amount(request.getAmount())
                .paid(request.getPaid() != null ? request.getPaid() : false)
                .build();

        TuitionFee savedFee = tuitionFeeRepository.save(fee);
        return tuitionFeeMapper.toTuitionFeeResponse(savedFee);
    }

}
