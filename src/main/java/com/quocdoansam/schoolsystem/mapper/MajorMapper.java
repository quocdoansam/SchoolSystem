package com.quocdoansam.schoolsystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.quocdoansam.schoolsystem.dto.request.MajorCreationRequest;
import com.quocdoansam.schoolsystem.dto.response.MajorResponse;
import com.quocdoansam.schoolsystem.entity.Major;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MajorMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "students", ignore = true)
    Major toMajorCreationRequest(MajorCreationRequest request);

    MajorResponse toMajorResponse(Major major);
}
