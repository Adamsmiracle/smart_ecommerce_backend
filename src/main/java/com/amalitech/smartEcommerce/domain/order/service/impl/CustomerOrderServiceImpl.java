package com.amalitech.smartEcommerce.domain.order.service.impl;

import com.amalitech.smartEcommerce.domain.order.dto.OrderLineDto;
import com.amalitech.smartEcommerce.domain.order.dto.OrderRequest;
import com.amalitech.smartEcommerce.domain.order.dto.OrderResponse;
import com.amalitech.smartEcommerce.domain.order.entity.CustomerOrder;
import com.amalitech.smartEcommerce.domain.order.entity.OrderLine;
import com.amalitech.smartEcommerce.domain.order.mapper.OrderMapper;
import com.amalitech.smartEcommerce.domain.order.repository.CustomerOrderRepository;
import com.amalitech.smartEcommerce.domain.order.service.CustomerOrderService;
import com.amalitech.smartEcommerce.domain.product.entity.ProductItem;
import com.amalitech.smartEcommerce.domain.product.repository.ProductItemRepository;
import com.amalitech.smartEcommerce.domain.user.entity.AppUser;
import com.amalitech.smartEcommerce.domain.user.repository.AppUserRepository;
import com.amalitech.smartEcommerce.exception.InsufficientStockException;
import com.amalitech.smartEcommerce.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {

    private final CustomerOrderRepository repository;
    private final AppUserRepository userRepository;
    private final ProductItemRepository productItemRepository;
    private final OrderMapper mapper;

    @Override
    @Transactional
    public CustomerOrder create(CustomerOrder order) {
        return repository.save(order);
    }

    @Override
    public CustomerOrder getById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found: " + id));
    }

    @Override
    public Page<CustomerOrder> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    @Transactional
    public CustomerOrder update(UUID id, CustomerOrder updates) {
        CustomerOrder existing = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found: " + id));
        // minimal update: update order status, shipping, payment and order total if provided
        if (updates.getOrderStatus() != null) existing.setOrderStatus(updates.getOrderStatus());
        if (updates.getPaymentMethod() != null) existing.setPaymentMethod(updates.getPaymentMethod());
        if (updates.getShippingAddress() != null) existing.setShippingAddress(updates.getShippingAddress());
        if (updates.getShippingMethod() != null) existing.setShippingMethod(updates.getShippingMethod());
        if (updates.getOrderTotal() != null) existing.setOrderTotal(updates.getOrderTotal());
        // Not modifying orderLines here; specialist endpoints can handle lines
        return repository.save(existing);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        CustomerOrder existing = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found: " + id));
        repository.delete(existing);
    }

    @Override
    @Transactional
    public OrderResponse createFromRequest(OrderRequest request) {
        if (request == null) throw new IllegalArgumentException("Request cannot be null");

        AppUser user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + request.getUserId()));

        CustomerOrder order = mapper.fromRequest(request);
        order.setUser(user);

        // Process order lines
        if (request.getOrderLines() != null) {
            for (OrderLineDto dto : request.getOrderLines()) {
                ProductItem item = productItemRepository.findById(dto.getProductItemId())
                        .orElseThrow(() -> new ResourceNotFoundException("Product item not found: " + dto.getProductItemId()));

                // check stock
                if (item.getQtyInStock() < dto.getQty()) {
                    throw new InsufficientStockException(item.getProduct().getName(), item.getQtyInStock(), dto.getQty());
                }

                // reserve by decrementing qtyInStock here for simplicity
                item.setQtyInStock(item.getQtyInStock() - dto.getQty());
                productItemRepository.save(item);

                OrderLine ol = mapper.fromDto(dto);
                ol.setProductItem(item);
                order.addOrderLine(ol);
            }
        }

        // compute total - iterate list
        double total = order.getOrderLines().stream()
                .mapToDouble(l -> l.getPrice() != null ? l.getPrice() * l.getQty() : 0)
                .sum();
        order.setOrderTotal(total);

        CustomerOrder saved = repository.save(order);
        return mapper.toResponse(saved);
    }

    @Override
    public Page<CustomerOrder> findByUserId(UUID userId, Pageable pageable) {
        return repository.findByUser_Id(userId, pageable);
    }

    @Override
    public Page<CustomerOrder> findByOrderStatusId(UUID statusId, Pageable pageable) {
        return repository.findByOrderStatus_Id(statusId, pageable);
    }

    @Override
    public Page<CustomerOrder> findByDateRange(LocalDate start, LocalDate end, Pageable pageable) {
        return repository.findByOrderDateBetween(start, end, pageable);
    }

    @Override
    public Page<CustomerOrder> findByTotalRange(Double minTotal, Double maxTotal, Pageable pageable) {
        return repository.findByOrderTotalBetween(minTotal, maxTotal, pageable);
    }

    @Override
    public Page<CustomerOrder> findByShippingMethodId(UUID shippingMethodId, Pageable pageable) {
        return repository.findByShippingMethod_Id(shippingMethodId, pageable);
    }
}
