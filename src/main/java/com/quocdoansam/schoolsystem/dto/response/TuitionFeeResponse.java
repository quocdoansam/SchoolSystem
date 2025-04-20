package com.quocdoansam.schoolsystem.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
public class TuitionFeeResponse {
    String id;
    BigDecimal amount;
    Boolean paid;
    LocalDateTime updatedAt;
    LocalDateTime createdAt;
}
