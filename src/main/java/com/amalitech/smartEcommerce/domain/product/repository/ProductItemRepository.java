package com.amalitech.smartEcommerce.domain.product.repository;

import com.amalitech.smartEcommerce.domain.product.entity.ProductItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductItemRepository extends JpaRepository<ProductItem, UUID> {

    Page<ProductItem> findAll(Pageable page);

//    Page<ProductItem> findBY

    Page<ProductItem> findByProduct_Id(UUID productId, Pageable pageable);

    // Inventory helpers
    List<ProductItem> findByQtyInStockLessThan(Integer threshold);
}
