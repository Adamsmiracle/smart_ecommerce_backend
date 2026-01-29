package com.amalitech.smartEcommerce.domain.cart.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "UpdateCartRequest", description = "Replaces the current items in a cart with the provided list")
public class UpdateCartRequest {
    // Full replacement of items in the cart. If null or empty, the cart will end up with no items.
    @Schema(description = "The new set of items for the cart. Null or empty clears the cart")
    private List<CreateCartItemRequest> items;
}
