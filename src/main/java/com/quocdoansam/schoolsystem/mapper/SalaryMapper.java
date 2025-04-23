package com.quocdoansam.schoolsystem.mapper;

import java.math.BigDecimal;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.quocdoansam.schoolsystem.dto.request.SalaryCreationRequest;
import com.quocdoansam.schoolsystem.dto.request.SalaryUpdateRequest;
import com.quocdoansam.schoolsystem.dto.response.SalaryResponse;
import com.quocdoansam.schoolsystem.entity.Salary;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SalaryMapper {
    @Mapping(target = "totalAmount", expression = "java(calculateTotal(request))")
    @Mapping(target = "teacher", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "monthSalary", ignore = true)
    Salary toSalaryCreationRequest(SalaryCreationRequest request);

    default BigDecimal calculateTotal(SalaryCreationRequest req) {
        BigDecimal bonus = req.getBonus() != null ? req.getBonus() : BigDecimal.ZERO;
        BigDecimal deduction = req.getDeduction() != null ? req.getDeduction() : BigDecimal.ZERO;
        return req.getBaseAmount().add(bonus).subtract(deduction);
    }

    @Mapping(target = "teacherId", source = "teacher.id")
    @Mapping(target = "teacherName", source = "teacher.name")
    SalaryResponse toSalaryResponse(Salary salary);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "teacher", ignore = true)
    @Mapping(target = "totalAmount", expression = "java(calculateTotal(request))")
    Salary toSalaryUpdateRequest(SalaryUpdateRequest request, @MappingTarget Salary salary);
}
