package com.amalitech.smartEcommerce.domain.product.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ProductRequest {

    @NotNull(message = "Product name must not be empty")
    private String name;

    @NotNull(message = "Provide product description")
    private String description;
    private String productImage;
}

