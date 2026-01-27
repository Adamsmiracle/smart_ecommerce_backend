package com.amalitech.smartEcommerce.domain.cart.repository;

import com.amalitech.smartEcommerce.domain.cart.entity.ShoppingCart;
import com.amalitech.smartEcommerce.domain.user.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, UUID> {
    Optional<ShoppingCart> findByUser(AppUser user);
}
