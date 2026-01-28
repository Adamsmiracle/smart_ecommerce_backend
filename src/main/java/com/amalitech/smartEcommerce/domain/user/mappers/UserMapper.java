package com.amalitech.smartEcommerce.domain.user.mappers;

import com.amalitech.smartEcommerce.domain.user.dto.CreateUserRequest;
import com.amalitech.smartEcommerce.domain.user.dto.UserResponse;
import com.amalitech.smartEcommerce.domain.user.entity.AppUser;

public class UserMapper {

    public static UserResponse toUserResponse(AppUser u) {
        if (u == null) return null;
        UserResponse r = new UserResponse();
        r.setId(u.getId());
        r.setCreatedAt(u.getCreatedAt());
        r.setUpdatedAt(u.getUpdatedAt());
        r.setEmail(u.getEmailAddress());
        r.setFirstname(u.getFirstName());
        r.setLastname(u.getLastName());
        r.setPhoneNumber(u.getPhoneNumber());
        r.setRole(u.getRole() != null ? u.getRole().getValue() : null);
        r.setStatus(u.getStatus() != null ? u.getStatus().name() : null);
        return r;
    }

    public static AppUser toUserEntity(CreateUserRequest r) {
        AppUser user = new AppUser();
        if (r == null) return null;
        user.setEmailAddress(r.getEmail());
        user.setFirstName(r.getFirstname());
        user.setLastName(r.getLastname());
        user.setPhoneNumber(r.getPhoneNumber());
        user.setRole(r.getRole());
        user.setStatus(r.getStatus());
        return user;
    }


}
