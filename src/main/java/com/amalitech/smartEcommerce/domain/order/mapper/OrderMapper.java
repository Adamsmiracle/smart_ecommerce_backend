package com.amalitech.smartEcommerce.domain.order.mapper;

import com.amalitech.smartEcommerce.domain.order.dto.OrderLineDto;
import com.amalitech.smartEcommerce.domain.order.dto.OrderRequest;
import com.amalitech.smartEcommerce.domain.order.dto.OrderResponse;
import com.amalitech.smartEcommerce.domain.order.entity.CustomerOrder;
import com.amalitech.smartEcommerce.domain.order.entity.OrderLine;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Simple manual mapper for orders. Kept intentionally minimal — service is responsible for
 * resolving associations (users, product items, payment methods, addresses, etc.).
 */
@Component
public class OrderMapper {

    public OrderResponse toResponse(CustomerOrder order) {
        if (order == null) return null;
        OrderResponse resp = new OrderResponse();
        resp.setId(order.getId());
        resp.setUserId(order.getUser() != null ? order.getUser().getId() : null);
        resp.setOrderDate(order.getOrderDate());
        resp.setOrderTotal(order.getOrderTotal());
        resp.setOrderStatusId(order.getOrderStatus() != null ? order.getOrderStatus().getId() : null);

        List<OrderLineDto> lines = order.getOrderLines() == null ? Collections.emptyList() :
                order.getOrderLines().stream().map(this::toLineDto).collect(Collectors.toList());
        resp.setOrderLines(lines);
        return resp;
    }

    public OrderLineDto toLineDto(OrderLine line) {
        if (line == null) return null;
        OrderLineDto d = new OrderLineDto();
        d.setId(line.getId());
        d.setProductItemId(line.getProductItem() != null ? line.getProductItem().getId() : null);
        d.setQty(line.getQty());
        d.setPrice(line.getPrice());
        return d;
    }

    public CustomerOrder fromRequest(OrderRequest req) {
        if (req == null) return null;
        CustomerOrder order = new CustomerOrder();
        order.setOrderDate(java.time.LocalDate.now());
        order.setOrderTotal(0.0);
        // order lines and associations should be set in service
        return order;
    }

    public OrderLine fromDto(OrderLineDto dto) {
        if (dto == null) return null;
        OrderLine ol = new OrderLine();
        ol.setQty(dto.getQty());
        ol.setPrice(dto.getPrice());
        // productItem association must be set by service by loading ProductItem by id
        return ol;
    }
}
