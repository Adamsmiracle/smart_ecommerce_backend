package com.amalitech.smartEcommerce.domain.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineDto {
    private UUID id;
    private UUID productItemId;
    private Integer qty;
    private Double price;
}

