package com.amalitech.smartEcommerce.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {
    GUEST("guest"),
    CUSTOMER("customer"),
    ADMIN("admin"),
    SELLER("seller");

    private final String value;
}