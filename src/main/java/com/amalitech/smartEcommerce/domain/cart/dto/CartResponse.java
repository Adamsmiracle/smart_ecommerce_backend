package com.amalitech.smartEcommerce.domain.cart.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "CartResponse", description = "Shopping cart returned by the API")
public class CartResponse {
    @Schema(description = "Cart ID", example = "7f000101-0000-0000-0000-000000000002")
    private UUID id;
    @Schema(description = "Creation timestamp (UTC)")
    private LocalDateTime createdAt;
    @Schema(description = "Last update timestamp (UTC)")
    private LocalDateTime updatedAt;
    @Schema(description = "Owner user ID")
    private UUID userId;
    @Schema(description = "Items contained in the cart")
    private List<CartItemResponse> items;
}
