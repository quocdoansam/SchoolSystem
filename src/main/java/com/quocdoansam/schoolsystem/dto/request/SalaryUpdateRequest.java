package com.quocdoansam.schoolsystem.dto.request;

import java.math.BigDecimal;
import java.time.YearMonth;

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
public class SalaryUpdateRequest {
    YearMonth monthSalary;
    BigDecimal baseAmount;
    BigDecimal bonus;
    BigDecimal deduction;
    SalaryStatus status;
    String note;
    Long teacherId;
}
