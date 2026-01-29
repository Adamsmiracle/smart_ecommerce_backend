package com.amalitech.smartEcommerce.domain.cart.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "CartItemResponse", description = "Cart item details returned by the API")
public class CartItemResponse {
    @Schema(description = "Cart item ID")
    private UUID id;
    @Schema(description = "Product item ID")
    private UUID productItemId;
    @Schema(description = "Quantity")
    private Integer quantity;
}
