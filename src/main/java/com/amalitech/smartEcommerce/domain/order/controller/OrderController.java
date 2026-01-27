package com.amalitech.smartEcommerce.domain.order.controller;

import com.amalitech.smartEcommerce.common.response.ApiResponse;
import com.amalitech.smartEcommerce.domain.order.entity.CustomerOrder;
import com.amalitech.smartEcommerce.domain.order.service.CustomerOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final CustomerOrderService service;

    public OrderController(CustomerOrderService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CustomerOrder>> create(@RequestBody CustomerOrder order) {
        CustomerOrder created = service.create(order);
        return ResponseEntity.ok(ApiResponse.success("Created", created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerOrder>> getById(@PathVariable("id") java.util.UUID id) {
        CustomerOrder o = service.getById(id);
        if (o == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(ApiResponse.success(o));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CustomerOrder>>> list(@RequestParam(defaultValue = "10") int limit,
                                                                  @RequestParam(defaultValue = "0") int offset) {
        return ResponseEntity.ok(ApiResponse.success(service.list(limit, offset)));
    }
}
