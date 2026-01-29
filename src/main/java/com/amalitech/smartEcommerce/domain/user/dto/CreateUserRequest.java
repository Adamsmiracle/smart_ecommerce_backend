package com.amalitech.smartEcommerce.domain.user.dto;

import com.amalitech.smartEcommerce.common.enums.UserRole;
import com.amalitech.smartEcommerce.common.enums.UserStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(name = "CreateUserRequest", description = "Payload to create a new user")
public class CreateUserRequest {
    @Schema(description = "User email address", example = "john.doe@example.com")
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;
    @Schema(description = "First name", example = "John")
    @NotBlank(message = "First name is required")
    private String firstname;
    @Schema(description = "Last name", example = "Doe")
    @NotBlank(message = "Last name is required")
    private String lastname;
    @Schema(description = "Phone number", example = "+15551234567")
    private String phoneNumber;
    @Schema(description = "Password", example = "Secrete@12")
    @NotBlank(message = "Password is required")
    private String password;
    @Schema(description = "User role")
    private UserRole role;
    @Schema(description = "User status")
    private UserStatus status;
}
