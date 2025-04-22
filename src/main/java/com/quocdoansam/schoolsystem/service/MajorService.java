package com.quocdoansam.schoolsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quocdoansam.schoolsystem.dto.request.MajorCreationRequest;
import com.quocdoansam.schoolsystem.dto.response.MajorResponse;
import com.quocdoansam.schoolsystem.entity.Major;
import com.quocdoansam.schoolsystem.enums.ErrorMessage;
import com.quocdoansam.schoolsystem.exception.BaseException;
import com.quocdoansam.schoolsystem.mapper.MajorMapper;
import com.quocdoansam.schoolsystem.repository.MajorRepository;

@Service
public class MajorService {
    @Autowired
    MajorRepository majorRepository;
    @Autowired
    MajorMapper majorMapper;

    public MajorResponse create(MajorCreationRequest request) {
        Major major = majorMapper.toMajorCreationRequest(request);
        return majorMapper.toMajorResponse(majorRepository.save(major));
    }

    public MajorResponse getMajorById(String id) {
        return majorRepository.findById(id).map(majorMapper::toMajorResponse)
                .orElseThrow(() -> new BaseException(ErrorMessage.MAJOR_NOT_FOUND));
    }

    public Major findById(String id) {
        return majorRepository.findById(id)
                .orElseThrow(() -> new BaseException(ErrorMessage.MAJOR_NOT_FOUND));
    }
}
