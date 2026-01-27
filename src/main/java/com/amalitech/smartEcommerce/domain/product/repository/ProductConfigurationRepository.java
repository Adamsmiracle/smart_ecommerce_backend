package com.amalitech.smartEcommerce.domain.product.repository;

import com.amalitech.smartEcommerce.domain.product.entity.ProductConfiguration;
import com.amalitech.smartEcommerce.domain.product.entity.ProductConfigurationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductConfigurationRepository extends JpaRepository<ProductConfiguration, ProductConfigurationId> {
    List<ProductConfiguration> findByIdProductItemId(UUID productItemId);

    List<ProductConfiguration> findByIdVariationOptionId(UUID variationOptionId);
}
