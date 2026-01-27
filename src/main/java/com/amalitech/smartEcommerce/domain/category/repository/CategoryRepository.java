package com.amalitech.smartEcommerce.domain.category.repository;

import com.amalitech.smartEcommerce.domain.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    List<Category> findByParentCategory_Id(UUID parentId);

    Page<Category> findByNameContainingIgnoreCase(String name, Pageable pageable);

    List<Category> findByParentCategoryIsNull();
}
