package com.amalitech.smartEcommerce.domain.product.dto;

import com.amalitech.smartEcommerce.domain.product.dto.ProductItemResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data

public class ProductResponse {

    @NotNull(message = "Product ID is required")
    private UUID id;

    @NotBlank(message = "Product name is required")
    private String name;

    private String description;

    private String productImage;

    private UUID categoryId;

    private String categoryName;

    @NotEmpty(message = "Product items cannot be empty")
    @Valid
    private List<ProductItemResponse> items;
}
