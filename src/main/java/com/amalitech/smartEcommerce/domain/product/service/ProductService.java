package com.amalitech.smartEcommerce.domain.product.service;

import com.amalitech.smartEcommerce.domain.product.dto.ProductResponse;
import com.amalitech.smartEcommerce.domain.product.entity.Product;
import com.amalitech.smartEcommerce.domain.product.entity.ProductItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ProductService {

    Product create(Product product);

    Product findById(UUID id);

    // return full products (with items) when searching by name
    Page<Product> searchProductByName(String name, int page, int size);

    Page<Product> listPaged(int page, int size);

    Page<Product> findByCategory(UUID categoryId, int page, int size);

    ProductResponse getProductDetail(UUID id);
}
