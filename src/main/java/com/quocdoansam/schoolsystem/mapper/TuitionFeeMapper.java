package com.quocdoansam.schoolsystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.quocdoansam.schoolsystem.dto.request.TuitionFeeCreationRequest;
import com.quocdoansam.schoolsystem.dto.response.TuitionFeeResponse;
import com.quocdoansam.schoolsystem.entity.TuitionFee;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, uses = {})
public interface TuitionFeeMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "student", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    TuitionFee toTuitionFeeCreationRequest(TuitionFeeCreationRequest request);

    TuitionFeeResponse toTuitionFeeResponse(TuitionFee tuitionFee);
}
