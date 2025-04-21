package com.quocdoansam.schoolsystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.quocdoansam.schoolsystem.dto.request.StudentCreationRequest;
import com.quocdoansam.schoolsystem.dto.request.StudentUpdateRequest;
import com.quocdoansam.schoolsystem.dto.response.StudentResponse;
import com.quocdoansam.schoolsystem.entity.Student;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface StudentMapper {
	@Mapping(target = "gpa", ignore = true)
	@Mapping(target = "updatedAt", ignore = true)
	@Mapping(target = "major", ignore = true)
	@Mapping(target = "address", ignore = true)
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "dob", ignore = true)
	@Mapping(target = "gender", ignore = true)
	@Mapping(target = "hometown", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "roles", ignore = true)
	@Mapping(target = "tuitionFees", ignore = true)
	Student toStudentCreationRequest(StudentCreationRequest request);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "major", ignore = true)
	@Mapping(target = "tuitionFees", ignore = true)
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "updatedAt", ignore = true)
	@Mapping(target = "roles", ignore = true)
	Student toStudentUpdateRequest(StudentUpdateRequest request, @MappingTarget Student student);

	@Mapping(target = "majorName", source = "major.name")
	StudentResponse toStudentResponse(Student student);
}
