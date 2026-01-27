package com.amalitech.smartEcommerce.domain.user.repository;

import com.amalitech.smartEcommerce.common.enums.UserRole;
import com.amalitech.smartEcommerce.common.enums.UserStatus;
import com.amalitech.smartEcommerce.domain.user.entity.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AppUserRepository extends JpaRepository<AppUser, UUID> {

    Optional<AppUser> findByEmailAddress(String emailAddress);

    boolean existsByEmailAddress(String emailAddress);

    // Find users by status
    List<AppUser> findByStatus(UserStatus status);

    // find users by role
    List<AppUser> findUserByRole(UserRole role);

    Page<AppUser> findByRole(UserRole role, Pageable pageable);

    List<AppUser> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstName, String lastName);
}
