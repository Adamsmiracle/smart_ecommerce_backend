package com.amalitech.smartEcommerce.domain.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ProductItemResponse {
    private UUID id;
    private String name;
    private String sku;
    private String image;
    private String price;
    private Integer qtyInStock;


}

