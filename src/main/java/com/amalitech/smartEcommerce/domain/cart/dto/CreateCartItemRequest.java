package com.amalitech.smartEcommerce.domain.cart.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "CreateCartItemRequest", description = "Represents a single item to be added to a cart")
public class CreateCartItemRequest {
    @NotNull
    @Schema(description = "Product item identifier", example = "4e5b7a8c-2d84-4833-9ce9-8b8d2ec0e6b1")
    private UUID productItemId;

    @NotNull
    @Min(1)
    @Schema(description = "Quantity to add", example = "2", minimum = "1")
    private Integer quantity;
}
