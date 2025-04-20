package com.quocdoansam.schoolsystem.dto.request;

import java.math.BigDecimal;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TuitionFeeCreationRequest {
    Long studentId;
    BigDecimal amount;
    Boolean paid;
}
