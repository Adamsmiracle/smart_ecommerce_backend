package com.amalitech.smartEcommerce.domain.cart.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "CreateCartRequest", description = "Payload to create a shopping cart for a user with optional initial items")
public class CreateCartRequest {
    @Schema(description = "User ID the cart belongs to", example = "b1e29b1c-7c4b-4b18-9d8e-6f0a8d0f9f1a", requiredMode = Schema.RequiredMode.REQUIRED)
    private UUID userId;

    @Schema(description = "Initial items to add to the cart", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private List<CreateCartItemRequest> items;
}
