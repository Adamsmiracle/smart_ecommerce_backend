package com.amalitech.smartEcommerce.domain.cart.controller;

import com.amalitech.smartEcommerce.common.response.ApiResponse;
import com.amalitech.smartEcommerce.domain.cart.entity.ShoppingCart;
import com.amalitech.smartEcommerce.domain.cart.service.ShoppingCartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/carts")
public class CartController {

    private final ShoppingCartService shoppingCartService;

    public CartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ShoppingCart>> create(@RequestBody ShoppingCart cart) {
        ShoppingCart created = shoppingCartService.create(cart);
        return ResponseEntity.ok(ApiResponse.success("Created", created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ShoppingCart>> getById(@PathVariable("id") java.util.UUID id) {
        ShoppingCart c = shoppingCartService.getById(id);
        if (c == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(ApiResponse.success(c));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ShoppingCart>>> list(@RequestParam(defaultValue = "10") int limit,
                                                                 @RequestParam(defaultValue = "0") int offset) {
        return ResponseEntity.ok(ApiResponse.success(shoppingCartService.list(limit, offset)));
    }
}
