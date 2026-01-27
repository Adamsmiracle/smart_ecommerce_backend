package com.amalitech.smartEcommerce.domain.product.service.impl;

import com.amalitech.smartEcommerce.domain.product.entity.ProductItem;
import com.amalitech.smartEcommerce.domain.product.repository.ProductItemRepository;
import com.amalitech.smartEcommerce.domain.product.service.ProductItemService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductItemServiceImpl implements ProductItemService {

    private final ProductItemRepository repository;

    public ProductItemServiceImpl(ProductItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProductItem create(ProductItem item) {
        return repository.save(item);
    }

    @Override
    public ProductItem getById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<ProductItem> list(int limit, int offset) {
        return repository.findAll();
    }



    @Override
    public Page<ProductItem> findByProductId(UUID productId, int page, int size) {
        return repository.findByProduct_Id(productId, PageRequest.of(page, size));
    }

    @Override
    public ProductItem update(ProductItem item) {
        return repository.save(item);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<ProductItem> findById(UUID itemId) {
        return repository.findById(itemId);
    }
}
