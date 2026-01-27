package com.amalitech.smartEcommerce.common.enums;

/**
 * Common order status enum used across the application.
 * Values map to typical e-commerce order lifecycle stages.
 */
public enum OrderStatusEnum {
    PENDING("Pending"),
    PROCESSING("Processing"),
    SHIPPED("Shipped"),
    DELIVERED("Delivered"),
    COMPLETED("Completed"),
    CANCELLED("Cancelled");


    private final String value;

    OrderStatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
