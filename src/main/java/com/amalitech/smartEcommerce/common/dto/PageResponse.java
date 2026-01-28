package com.amalitech.smartEcommerce.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse<P> {
    private int page;
    private int size;
    private int totalPages;
    private long totalElements;
    private List<P> items;

    public static <T, R> PageResponse<R> from(Page<T> page, Function<T, R> mapper) {
        List<R> items = page.stream().map(mapper).collect(Collectors.toList());
        return new PageResponse<>(page.getNumber(), page.getSize(), page.getTotalPages(), page.getTotalElements(), items);
    }
}
