package com.amalitech.smartEcommerce.domain.cart.repository;

import com.amalitech.smartEcommerce.domain.cart.entity.ShoppingCartItem;
import com.amalitech.smartEcommerce.domain.cart.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, UUID> {
    List<ShoppingCartItem> findByCart(ShoppingCart cart);

    void deleteByCart(ShoppingCart cart);
}
