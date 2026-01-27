package com.amalitech.smartEcommerce.domain.product.repository;

import com.amalitech.smartEcommerce.domain.product.entity.VariationOption;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface VariationOptionRepository extends JpaRepository<VariationOption, UUID> {
    List<VariationOption> findByVariation_Id(UUID variationId);

    Page<VariationOption> findByVariation_Id(UUID variationId, Pageable pageable);
}
