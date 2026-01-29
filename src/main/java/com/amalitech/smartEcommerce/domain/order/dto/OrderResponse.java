package com.amalitech.smartEcommerce.domain.order.dto;

import jdk.jshell.Snippet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    private UUID id;
    private UUID userId;
    private LocalDate orderDate;
    private Double orderTotal;
    private UUID orderStatusId;
    private List<OrderLineDto> orderLines;


}

