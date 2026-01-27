package com.amalitech.smartEcommerce.domain.product.service.impl;

import com.amalitech.smartEcommerce.domain.product.dto.ProductResponse;
import com.amalitech.smartEcommerce.domain.product.entity.Product;
import com.amalitech.smartEcommerce.domain.product.mapper.ProductMapper;
import com.amalitech.smartEcommerce.domain.product.repository.ProductRepository;
import com.amalitech.smartEcommerce.domain.product.service.ProductService;
import com.amalitech.smartEcommerce.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        if (product == null)
            return null;
        return productRepository.save(product);
    }

    @Override
    public Product findById(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }

    @Override
    public Product findDetailedById(UUID id) {
        return productRepository.findWithItemsById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }

    @Override
    public Page<Product> listPaged(int page, int size) {
        return productRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Page<Product> findByCategory(UUID categoryId, int page, int size) {
        return productRepository.findByCategory_Id(categoryId, PageRequest.of(page, size));
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponse getProductDetail(UUID id) {
        Product p = productRepository.findWithItemsById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        return ProductMapper.toDetailResponse(p, p.getItems());
    }
}
