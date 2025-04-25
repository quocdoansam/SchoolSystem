package com.quocdoansam.schoolsystem.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.quocdoansam.schoolsystem.enums.SalaryStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SalaryResponse {
    String id;
    String teacherId;
    String teacherName;
    BigDecimal baseAmount;
    BigDecimal bonus;
    BigDecimal deduction;
    BigDecimal totalAmount;
    SalaryStatus status;
    String note;
    String updatedBy;
    String createdBy;
    LocalDateTime updatedAt;
    LocalDateTime createdAt;
}
