package com.amalitech.smartEcommerce.domain.user.entity;

import com.amalitech.smartEcommerce.common.BaseEntity;
import com.amalitech.smartEcommerce.common.enums.UserRole;
import com.amalitech.smartEcommerce.common.enums.UserStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Table(name = "app_user", indexes = {
        @Index(name = "idx_user_email", columnList = "email_address"),
        @Index(name = "idx_user_id", columnList = "id")
})
public class AppUser extends BaseEntity {

    @NotBlank(message = "Email address is required")
    @Email(message = "Email should be valid")
    @Size(max = 100, message = "Email cannot exceed 100 characters")
    @Column(name = "email_address", nullable = false, unique = true, length = 100)
    private String emailAddress;

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be 2-50 characters")
    @Pattern(regexp = "^[A-Za-z\\s\\-'.]+$",
            message = "First name can only contain letters, spaces, hyphens, apostrophes, and periods")
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be 2-50 characters")
    @Pattern(regexp = "^[A-Za-z\\s\\-'.]+$",
            message = "Last name can only contain letters, spaces, hyphens, apostrophes, and periods")
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Size(max = 20, message = "Phone number cannot exceed 20 characters")
    @Pattern(regexp = "^[+]?[0-9\\s\\-\\(\\)]*$",
            message = "Phone number can only contain digits, spaces, hyphens, parentheses, and plus sign")
    @Column(name = "phone_number", length = 20)
    @NotNull(message = "Phone number is needed")
    private String phoneNumber;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 100, message = "Password must be 8-100 characters")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
            message = "Password must contain at least 1 digit, 1 lowercase, 1 uppercase, 1 special character, and no spaces"
    )
    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @NotNull(message = "Role is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 20)
    private UserRole role;

    @NotNull(message = "Status is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private UserStatus status = UserStatus.ACTIVE;  // Default to active


}