package com.amalitech.smartEcommerce.domain.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductWithQtyDto {
    private UUID id;
    private String name;
    private String productImage;
    private UUID categoryId;
    private String categoryName;
    private BigDecimal price;
    private Long totalQtyInStock;
}
