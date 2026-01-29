package com.amalitech.smartEcommerce.domain.order.controller;

import com.amalitech.smartEcommerce.common.dto.PageResponse;
import com.amalitech.smartEcommerce.common.response.ApiResponse;
import com.amalitech.smartEcommerce.domain.order.dto.OrderRequest;
import com.amalitech.smartEcommerce.domain.order.dto.OrderResponse;
import com.amalitech.smartEcommerce.domain.order.entity.CustomerOrder;
import com.amalitech.smartEcommerce.domain.order.mapper.OrderMapper;
import com.amalitech.smartEcommerce.domain.order.service.CustomerOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
@Tag(name = "Order", description = "Order management")
public class OrderController {

    private final CustomerOrderService service;
    private final OrderMapper mapper;

    public OrderController(CustomerOrderService service, OrderMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    @Operation(summary = "Create order")
    public ResponseEntity<ApiResponse<OrderResponse>> create(@Valid @RequestBody OrderRequest req) {
        // Delegate creation from DTO to service which returns an OrderResponse
        OrderResponse resp = service.createFromRequest(req);
        return ResponseEntity.ok(ApiResponse.success("Created", resp));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get order by id")
    public ResponseEntity<ApiResponse<OrderResponse>> getById(@PathVariable("id") UUID id) {
        CustomerOrder o = service.getById(id);
        OrderResponse resp = mapper.toResponse(o);
        return ResponseEntity.ok(ApiResponse.success(resp));
    }

    @GetMapping
    @Operation(summary = "List orders")
    public ResponseEntity<ApiResponse<PageResponse<OrderResponse>>> list(Pageable pageable) {
        Page<CustomerOrder> page = service.list(pageable);
        PageResponse<OrderResponse> response = PageResponse.from(page, mapper::toResponse);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @GetMapping("/by-user/{userId}")
    @Operation(summary = "Get orders by user id")
    public ResponseEntity<ApiResponse<PageResponse<OrderResponse>>> getByUser(
            @PathVariable("userId") UUID userId,
            Pageable pageable
    ) {
        Page<CustomerOrder> page = service.findByUserId(userId, pageable);
        PageResponse<OrderResponse> response = PageResponse.from(page, mapper::toResponse);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update order")
    public ResponseEntity<ApiResponse<OrderResponse>> update(@PathVariable("id") UUID id, @Valid @RequestBody OrderRequest req) {
        // Map update fields to entity; only basic fields are updated in service
        CustomerOrder updates = mapper.fromRequest(req);
        CustomerOrder updated = service.update(id, updates);
        return ResponseEntity.ok(ApiResponse.success(mapper.toResponse(updated)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete order")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable("id") UUID id) {
        service.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Deleted", "deleted"));
    }
}
