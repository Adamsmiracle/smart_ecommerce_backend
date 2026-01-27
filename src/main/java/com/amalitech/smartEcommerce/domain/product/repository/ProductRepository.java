package com.amalitech.smartEcommerce.domain.product.repository;

import com.amalitech.smartEcommerce.domain.product.dto.ProductWithQtyDto;
import com.amalitech.smartEcommerce.domain.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    // Find all products by category id (UUID)
    Page<Product> findByCategory_Id(UUID categoryId, Pageable pageable);

    // search by name
    Page<Product> findByNameContainingIgnoreCase(String term, Pageable pageable);

    List<Product> findByCategory_Id(UUID categoryId);

    @Query("select p from Product p left join fetch p.items where p.id = :id")
    Optional<Product> findWithItemsById(@Param("id") UUID id);

    @Query("select new com.amalitech.smartEcommerce.domain.product.dto.ProductWithQtyDto(" +
            "p.id, p.name, p.productImage, p.category.id, p.category.name, min(i.price), coalesce(sum(i.qtyInStock), 0)) " +
            "from Product p left join p.items i " +
            "where (:categoryId is null or p.category.id = :categoryId) " +
            "group by p.id, p.name, p.productImage, p.category.id, p.category.name")
    Page<ProductWithQtyDto> findProductsByCategory(@Param("categoryId") UUID categoryId, Pageable pageable);
}
