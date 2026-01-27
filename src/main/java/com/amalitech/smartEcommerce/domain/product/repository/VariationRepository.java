package com.amalitech.smartEcommerce.domain.product.repository;

import com.amalitech.smartEcommerce.domain.product.entity.Variation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface VariationRepository extends JpaRepository<Variation, UUID> {
    List<Variation> findByCategory_Id(UUID categoryId);

    Page<Variation> findByCategory_Id(UUID categoryId, Pageable pageable);

    List<Variation> findByNameContainingIgnoreCase(String term);
}
