package com.amalitech.smartEcommerce.domain.category.service.impl;

import com.amalitech.smartEcommerce.domain.category.entity.Category;
import com.amalitech.smartEcommerce.domain.category.repository.CategoryRepository;
import com.amalitech.smartEcommerce.domain.category.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getById(UUID id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public List<Category> list(int limit, int offset) {
        return categoryRepository.findAll();
    }
}

