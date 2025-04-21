package com.quocdoansam.schoolsystem.dto.request;

import jakarta.validation.constraints.NotBlank;
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
public class TeacherCreationRequest {
    @NotBlank(message = "The password is required.")
    String password;

    @NotBlank(message = "The name is required.")
    String name;

    @NotBlank(message = "The email is required.")
    String email;

    @NotBlank(message = "The phone number is required.")
    String phoneNumber;

    @NotBlank(message = "The dempartment is required.")
    String department;
}
