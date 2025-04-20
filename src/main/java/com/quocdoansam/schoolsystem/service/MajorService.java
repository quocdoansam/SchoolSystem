package com.quocdoansam.schoolsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quocdoansam.schoolsystem.dto.request.MajorCreationRequest;
import com.quocdoansam.schoolsystem.dto.response.MajorResponse;
import com.quocdoansam.schoolsystem.entity.Major;
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
}
