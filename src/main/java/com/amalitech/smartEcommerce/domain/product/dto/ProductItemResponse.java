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
    private String sku;
    private String image;
    private String price;
    private Integer qtyInStock;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public String getPrice() { return price; }
    public void setPrice(String price) { this.price = price; }

    public Integer getQtyInStock() { return qtyInStock; }
    public void setQtyInStock(Integer qtyInStock) { this.qtyInStock = qtyInStock; }
}

