package com.amalitech.smartEcommerce.domain.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    @NotNull
    private UUID userId;

    private UUID paymentMethodId;
    private UUID shippingAddressId;
    private UUID shippingMethodId;

    private List<OrderLineDto> orderLines;
}

