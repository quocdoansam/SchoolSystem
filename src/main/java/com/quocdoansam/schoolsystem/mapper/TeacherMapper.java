package com.quocdoansam.schoolsystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.quocdoansam.schoolsystem.dto.request.TeacherCreationRequest;
import com.quocdoansam.schoolsystem.dto.response.TeacherResponse;
import com.quocdoansam.schoolsystem.entity.Teacher;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TeacherMapper {
    Teacher toTeacherCreationRequest(TeacherCreationRequest request);

    TeacherResponse toTeacherResponse(Teacher teacher);
}
