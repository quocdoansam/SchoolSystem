package com.quocdoansam.schoolsystem.dto.response;

import java.time.LocalDateTime;

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
    String amount;
    String paid;
    LocalDateTime updatedAt;
    LocalDateTime createdAt;
}
