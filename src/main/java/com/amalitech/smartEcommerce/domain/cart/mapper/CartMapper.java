package com.amalitech.smartEcommerce.domain.cart.mapper;

import com.amalitech.smartEcommerce.domain.cart.dto.CartResponse;
import com.amalitech.smartEcommerce.domain.cart.dto.CreateCartRequest;
import com.amalitech.smartEcommerce.domain.cart.dto.CartItemResponse;
import com.amalitech.smartEcommerce.domain.cart.entity.ShoppingCart;
import com.amalitech.smartEcommerce.domain.cart.entity.ShoppingCartItem;
import com.amalitech.smartEcommerce.domain.user.entity.AppUser;
import java.util.List;
import java.util.stream.Collectors;

public class CartMapper {

    public static ShoppingCart toEntity(CreateCartRequest r, AppUser user) {
        if (r == null) return null;
        return ShoppingCart.builder()
                .user(user)
                .build();
    }

    public static CartResponse toResponse(ShoppingCart c) {
        if (c == null) return null;
        List<CartItemResponse> items = null;
        if (c.getItems() != null) {
            items = c.getItems().stream()
                    .map(CartMapper::toItemResponse)
                    .collect(Collectors.toList());
        }
        return new CartResponse(
                c.getId(),
                c.getCreatedAt(),
                c.getUpdatedAt(),
                c.getUser() != null ? c.getUser().getId() : null,
                items
        );
    }

    private static CartItemResponse toItemResponse(ShoppingCartItem item) {
        if (item == null) return null;
        return new CartItemResponse(
                item.getId(),
                item.getProductItem() != null ? item.getProductItem().getId() : null,
                item.getQuantity()
        );
    }
}
