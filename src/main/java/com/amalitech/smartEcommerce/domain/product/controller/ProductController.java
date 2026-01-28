package com.amalitech.smartEcommerce.domain.product.controller;

import com.amalitech.smartEcommerce.common.dto.PageResponse;
import com.amalitech.smartEcommerce.common.response.ApiResponse;
import com.amalitech.smartEcommerce.domain.product.dto.ProductItemResponse;
import com.amalitech.smartEcommerce.domain.product.dto.ProductRequest;
import com.amalitech.smartEcommerce.domain.product.dto.ProductResponse;
import com.amalitech.smartEcommerce.domain.product.entity.Product;
import com.amalitech.smartEcommerce.domain.product.entity.ProductItem;
import com.amalitech.smartEcommerce.domain.product.mapper.ProductMapper;
import com.amalitech.smartEcommerce.domain.product.service.ProductItemService;
import com.amalitech.smartEcommerce.domain.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
@Tag(name = "Products", description = "Manage products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;
    private final ProductItemService itemService;

    @GetMapping
    @Operation(summary = "List products (paged)")
    public ResponseEntity<ApiResponse<PageResponse<ProductResponse>>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<Product> productPage = service.listPaged(page, size);
        PageResponse<ProductResponse> response = PageResponse.from(productPage, ProductMapper::toProductResponse);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @GetMapping("/by-category")
    @Operation(summary = "List products by category (paged)")
    public ResponseEntity<ApiResponse<PageResponse<ProductResponse>>> byCategory(
            @RequestParam UUID categoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<Product> productPage = service.findByCategory(categoryId, page, size);
        PageResponse<ProductResponse> response = PageResponse.from(productPage, ProductMapper::toProductResponse);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "Get product by id (detail)")
    public ResponseEntity<ApiResponse<ProductResponse>> getById(@PathVariable UUID id) {
        ProductResponse p = service.getProductDetail(id);
        return ResponseEntity.ok(ApiResponse.success(p));
    }

    @GetMapping("/{name}")
    @Operation(summary = "search for product using name")
    public ResponseEntity<ApiResponse<PageResponse<ProductResponse>>> getByName(
            @PathVariable String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
    Page<Product> productPage = service.searchProductByName(name, page, size );
    PageResponse<ProductResponse> response = PageResponse.from(productPage, ProductMapper::toProductResponse);
    return ResponseEntity.ok(ApiResponse.success(response));
    }

    @PostMapping
    @Operation(summary = "Create a product")
    public ResponseEntity<ApiResponse<ProductResponse>> create(@Valid @RequestBody ProductRequest req) {
        Product p = ProductMapper.fromProductRequest(req);
        Product created = service.create(p);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Product created", ProductMapper.toProductResponse(created)));
    }

    // === SKU ENDPOINTS ===

    @GetMapping("/{productId}/items")
    @Operation(summary = "List product items (paged)")
    public ResponseEntity<ApiResponse<PageResponse<ProductItemResponse>>> listItems(
            @PathVariable UUID productId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<ProductItem> itemPage = itemService.findByProductId(productId, page, size);
        PageResponse<ProductItemResponse> resp = PageResponse.from(itemPage, ProductMapper::toItemResponse);
        return ResponseEntity.ok(ApiResponse.success(resp));
    }

    @GetMapping("/items/{itemId}")
    @Operation(summary = "Get product item by ID")
    public ResponseEntity<ApiResponse<ProductItemResponse>> getItem(@PathVariable UUID itemId) {
        ProductItem item = itemService.getById(itemId);
        return ResponseEntity.ok(ApiResponse.success(ProductMapper.toItemResponse(item)));
    }

    @PostMapping("/items")
    @Operation(summary = "Create product item")
    public ResponseEntity<ApiResponse<ProductItemResponse>> createItem(@Valid @RequestBody com.amalitech.smartEcommerce.domain.product.dto.ProductItemRequest req) {
        Product product = service.findById(req.getProductId());
        ProductItem item = ProductMapper.fromItemRequest(req, product);
        ProductItem created = itemService.create(item);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Item created", ProductMapper.toItemResponse(created)));
    }

    @PutMapping("/items/{itemId}")
    @Operation(summary = "Update product item")
    public ResponseEntity<ApiResponse<ProductItemResponse>> updateItem(
            @PathVariable UUID itemId,
            @Valid @RequestBody com.amalitech.smartEcommerce.domain.product.dto.ProductItemRequest req) {
        ProductItem existing = itemService.getById(itemId);
        existing.setSku(req.getSku());
        existing.setPrice(req.getPrice());
        existing.setQtyInStock(req.getQtyInStock());
        existing.setImage(req.getImage());
        existing.setProduct(service.findById(req.getProductId()));
        ProductItem updated = itemService.update(existing);
        return ResponseEntity.ok(ApiResponse.success("Item updated", ProductMapper.toItemResponse(updated)));
    }

    @DeleteMapping("/items/{itemId}")
    @Operation(summary = "Delete product item")
    public ResponseEntity<ApiResponse<Void>> deleteItem(@PathVariable UUID itemId) {
        itemService.delete(itemId);
        return ResponseEntity.ok(ApiResponse.success("Item deleted", null));
    }
}
