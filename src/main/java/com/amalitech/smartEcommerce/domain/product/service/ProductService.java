package com.amalitech.smartEcommerce.domain.product.service;

import com.amalitech.smartEcommerce.domain.product.dto.ProductResponse;
import com.amalitech.smartEcommerce.domain.product.entity.Product;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface ProductService {

    Product create(Product product);

    Product findById(UUID id);

    Product findDetailedById(UUID id);

    Page<Product> listPaged(int page, int size);

    Page<Product> findByCategory(UUID categoryId, int page, int size);

    ProductResponse getProductDetail(UUID id);
}
