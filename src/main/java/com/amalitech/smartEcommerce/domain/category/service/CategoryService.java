package com.amalitech.smartEcommerce.domain.category.service;

import com.amalitech.smartEcommerce.domain.category.entity.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    Category create(Category category);
    Category getById(UUID id);
    List<Category> list(int limit, int offset);
}

