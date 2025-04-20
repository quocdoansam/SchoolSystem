package com.quocdoansam.schoolsystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.quocdoansam.schoolsystem.dto.response.TuitionFeeResponse;
import com.quocdoansam.schoolsystem.entity.TuitionFee;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TuitionFeeMapper {
    TuitionFeeResponse toTuitionFeeResponse(TuitionFee tuitionFee);
}
