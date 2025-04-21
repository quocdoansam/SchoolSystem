package com.quocdoansam.schoolsystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.quocdoansam.schoolsystem.dto.request.TeacherCreationRequest;
import com.quocdoansam.schoolsystem.dto.request.TeacherUpdateRequest;
import com.quocdoansam.schoolsystem.dto.response.TeacherResponse;
import com.quocdoansam.schoolsystem.entity.Teacher;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TeacherMapper {
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "address", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "dob", ignore = true)
    @Mapping(target = "gender", ignore = true)
    @Mapping(target = "hometown", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    Teacher toTeacherCreationRequest(TeacherCreationRequest request);

    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    Teacher toTeacherUpdateRequest(TeacherUpdateRequest request, @MappingTarget Teacher teacher);

    TeacherResponse toTeacherResponse(Teacher teacher);
}
