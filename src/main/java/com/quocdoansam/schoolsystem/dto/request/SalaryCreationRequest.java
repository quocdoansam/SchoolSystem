package com.quocdoansam.schoolsystem.dto.request;

import java.math.BigDecimal;

import com.quocdoansam.schoolsystem.enums.SalaryStatus;

import jakarta.validation.constraints.NotNull;
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
public class SalaryCreationRequest {
    @NotNull(message = "The teacher ID is required.")
    Long teacherId;
    BigDecimal baseAmount;

    @Builder.Default
    BigDecimal bonus = BigDecimal.ZERO;

    @Builder.Default
    BigDecimal deduction = BigDecimal.ZERO;
    SalaryStatus status;
    String note;
}
