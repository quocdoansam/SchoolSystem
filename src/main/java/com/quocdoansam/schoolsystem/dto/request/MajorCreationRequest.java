package com.quocdoansam.schoolsystem.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class MajorCreationRequest {
    @Size(min = 5, max = 255, message = "The major name must be least 5 up to 255 characters.")
    String name;

    @NotNull(message = "The tuition fees is required.")
    BigDecimal tuitionFees;
}
