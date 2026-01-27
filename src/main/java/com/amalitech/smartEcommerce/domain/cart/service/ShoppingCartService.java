package com.amalitech.smartEcommerce.domain.cart.service;

import com.amalitech.smartEcommerce.domain.cart.entity.ShoppingCart;

import java.util.List;
import java.util.UUID;

public interface ShoppingCartService {
    ShoppingCart create(ShoppingCart cart);
    ShoppingCart getById(UUID id);
    List<ShoppingCart> list(int limit, int offset);
}

