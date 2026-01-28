package com.amalitech.smartEcommerce.domain.user.dto;

import com.amalitech.smartEcommerce.common.enums.UserRole;
import com.amalitech.smartEcommerce.common.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateUserRequest {
    private String email;
    private String firstname;
    private String lastname;
    private String phoneNumber;
    private UserRole role;
    private UserStatus status;
}
