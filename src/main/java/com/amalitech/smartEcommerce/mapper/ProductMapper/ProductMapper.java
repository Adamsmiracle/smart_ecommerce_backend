package com.amalitech.smartEcommerce.mapper.ProductMapper;

import com.amalitech.smartEcommerce.domain.product.entity.Product;
import com.amalitech.smartEcommerce.domain.product.dto.ProductRequest;
import com.amalitech.smartEcommerce.domain.product.dto.ProductResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toEntity(ProductRequest req) {
        if (req == null) return null;
        Product p = new Product();
        p.setName(req.getName());
        p.setDescription(req.getDescription());
        p.setProductImage(req.getProductImage());
        return p;
    }

    public ProductResponse toResponse(Product entity) {
        if (entity == null) return null;
        ProductResponse r = new ProductResponse();
        r.setId(entity.getId());
        r.setName(entity.getName());
        r.setDescription(entity.getDescription());
        r.setProductImage(entity.getProductImage());
        return r;
    }
}
