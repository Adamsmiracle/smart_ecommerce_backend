package com.amalitech.smartEcommerce.domain.cart.controller;

import com.amalitech.smartEcommerce.common.response.ApiResponse;
import com.amalitech.smartEcommerce.domain.cart.dto.CartResponse;
import com.amalitech.smartEcommerce.domain.cart.dto.CreateCartRequest;
import com.amalitech.smartEcommerce.domain.cart.dto.UpdateCartRequest;
import com.amalitech.smartEcommerce.domain.cart.entity.ShoppingCart;
import com.amalitech.smartEcommerce.domain.cart.mapper.CartMapper;
import com.amalitech.smartEcommerce.domain.cart.service.ShoppingCartService;
import com.amalitech.smartEcommerce.domain.user.entity.AppUser;
import com.amalitech.smartEcommerce.domain.user.service.AppUserService;
import com.amalitech.smartEcommerce.domain.cart.entity.ShoppingCartItem;
import com.amalitech.smartEcommerce.domain.product.entity.ProductItem;
import com.amalitech.smartEcommerce.domain.product.service.ProductItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/carts")
@Tag(name = "Carts", description = "Shopping cart management")
public class CartController {

    private final ShoppingCartService shoppingCartService;
    private final AppUserService appUserService;
    private final ProductItemService productItemService;

    public CartController(ShoppingCartService shoppingCartService, AppUserService appUserService, ProductItemService productItemService) {
        this.shoppingCartService = shoppingCartService;
        this.appUserService = appUserService;
        this.productItemService = productItemService;
    }

    @PostMapping
    @Transactional
    @Operation(summary = "Create cart", description = "Creates a shopping cart for a user with optional initial items")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Cart created successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request payload"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Related resource not found (user or product item)")
    })
    public ResponseEntity<ApiResponse<CartResponse>> create(@RequestBody CreateCartRequest request) {
        if (request == null || request.getUserId() == null) {
            return ResponseEntity.badRequest().body(ApiResponse.error("userId is required"));
        }
        AppUser user = appUserService.getById(request.getUserId());
        if (user == null) {
            return ResponseEntity.status(404).body(ApiResponse.notFound("User not found"));
        }
        ShoppingCart toCreate = CartMapper.toEntity(request, user);
//        Add items to the cart
        if (request.getItems() != null && !request.getItems().isEmpty()) {
            for (var reqItem : request.getItems()) {
                if (reqItem == null || reqItem.getProductItemId() == null) {
                    return ResponseEntity.badRequest().body(ApiResponse.error("items[].productItemId is required"));
                }
                if (reqItem.getQuantity() == null || reqItem.getQuantity() < 1) {
                    return ResponseEntity.badRequest().body(ApiResponse.error("items[].quantity must be >= 1"));
                }
                ProductItem productItem = productItemService.getById(reqItem.getProductItemId());
                if (productItem == null) {
                    return ResponseEntity.status(404).body(ApiResponse.notFound("Product item not found: " + reqItem.getProductItemId()));
                }
                ShoppingCartItem item = ShoppingCartItem.builder()
                        .cart(toCreate)
                        .productItem(productItem)
                        .quantity(reqItem.getQuantity())
                        .build();
                toCreate.getItems().add(item);
            }
        }
        ShoppingCart created = shoppingCartService.create(toCreate);
        CartResponse response = CartMapper.toResponse(created);
        return ResponseEntity.ok(ApiResponse.success("Created", response));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get cart by id", description = "Retrieves a shopping cart by its identifier")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Cart found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Cart not found")
    })
    public ResponseEntity<ApiResponse<CartResponse>> getById(
            @Parameter(description = "Cart ID", required = true) @PathVariable("id") UUID id) {
        ShoppingCart c = shoppingCartService.getById(id);
        if (c == null) return ResponseEntity.status(404).body(ApiResponse.notFound("Cart not found"));
        return ResponseEntity.ok(ApiResponse.success(CartMapper.toResponse(c)));
    }

    @GetMapping
    @Operation(summary = "List carts", description = "Lists carts (non-paginated in current implementation)")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "List of carts returned")
    })
    public ResponseEntity<ApiResponse<List<CartResponse>>> list(@Parameter(description = "Maximum items to return") @RequestParam(defaultValue = "10") int limit,
                                                                 @Parameter(description = "Offset for listing") @RequestParam(defaultValue = "0") int offset) {
        List<CartResponse> responses = shoppingCartService.list(limit, offset)
                .stream()
                .map(CartMapper::toResponse)
                .toList();
        return ResponseEntity.ok(ApiResponse.success(responses));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update cart items", description = "Replaces all items in the cart with the provided list")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Cart updated successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request payload"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Cart or product item not found")
    })
    public ResponseEntity<ApiResponse<CartResponse>> update(
            @Parameter(description = "Cart ID", required = true) @PathVariable("id") UUID id,
            @RequestBody UpdateCartRequest request
    ) {
        ShoppingCart existing = shoppingCartService.getById(id);
        if (existing == null) {
            return ResponseEntity.status(404).body(ApiResponse.notFound("Cart not found"));
        }

        // Replace items with the provided list (empty or null clears the cart)
        existing.getItems().clear();
        if (request != null && request.getItems() != null && !request.getItems().isEmpty()) {
            for (var reqItem : request.getItems()) {
                if (reqItem == null || reqItem.getProductItemId() == null) {
                    return ResponseEntity.badRequest().body(ApiResponse.error("items[].productItemId is required"));
                }
                if (reqItem.getQuantity() == null || reqItem.getQuantity() < 1) {
                    return ResponseEntity.badRequest().body(ApiResponse.error("items[].quantity must be >= 1"));
                }
                ProductItem productItem = productItemService.getById(reqItem.getProductItemId());
                if (productItem == null) {
                    return ResponseEntity.status(404).body(ApiResponse.notFound("Product item not found: " + reqItem.getProductItemId()));
                }
                ShoppingCartItem item = ShoppingCartItem.builder()
                        .cart(existing)
                        .productItem(productItem)
                        .quantity(reqItem.getQuantity())
                        .build();
                existing.getItems().add(item);
            }
        }

        ShoppingCart saved = shoppingCartService.update(existing);
        return ResponseEntity.ok(ApiResponse.success(CartMapper.toResponse(saved)));
    }
}
