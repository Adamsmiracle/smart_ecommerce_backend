package com.amalitech.smartEcommerce.domain.product.service;

import com.amalitech.smartEcommerce.domain.product.entity.ProductItem;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductItemService {
    ProductItem create(ProductItem item);
    ProductItem getById(UUID id);
    List<ProductItem> list(int limit, int offset);

    Page<ProductItem> findByProductId(UUID productId, int page, int size);

    ProductItem update(ProductItem item);
    void delete(UUID id);

    Optional<ProductItem> findById(UUID itemId);
}
