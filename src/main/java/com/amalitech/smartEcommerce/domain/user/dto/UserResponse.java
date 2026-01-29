package com.amalitech.smartEcommerce.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(name = "UserResponse", description = "User model returned by the API")
public class UserResponse {
    @Schema(description = "User ID", example = "7f000101-0000-0000-0000-000000000001")
    private UUID id;
    @Schema(description = "Creation timestamp (UTC)")
    private LocalDateTime createdAt;
    @Schema(description = "Last update timestamp (UTC)")
    private LocalDateTime updatedAt;
    @Schema(description = "Email address", example = "jane.doe@example.com")
    private String email;
    @Schema(description = "First name", example = "Jane")
    private String firstname;
    @Schema(description = "Last name", example = "Doe")
    private String lastname;
    @Schema(description = "Phone number", example = "+233555123456")
    private String phoneNumber;
    @Schema(description = "Role value", example = "customer")
    private String role;
    @Schema(description = "Status", example = "ACTIVE")
    private String status;

}
