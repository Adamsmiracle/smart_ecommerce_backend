package com.amalitech.smartEcommerce.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum UserStatus {
    ACTIVE("active"),
    INACTIVE("inactive");

    UserStatus(String active) {
    }
}
