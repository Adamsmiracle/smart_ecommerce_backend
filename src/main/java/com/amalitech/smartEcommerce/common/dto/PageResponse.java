package com.amalitech.smartEcommerce.common.dto;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PageResponse<P> {
    private int page;
    private int size;
    private int totalPages;
    private long totalElements;
    private List<P> items;

    public PageResponse() {}

    public PageResponse(int page, int size, int totalPages, long totalElements, List<P> items) {
        this.page = page;
        this.size = size;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.items = items;
    }

    public int getPage() { return page; }
    public void setPage(int page) { this.page = page; }
    public int getSize() { return size; }
    public void setSize(int size) { this.size = size; }
    public int getTotalPages() { return totalPages; }
    public void setTotalPages(int totalPages) { this.totalPages = totalPages; }
    public long getTotalElements() { return totalElements; }
    public void setTotalElements(long totalElements) { this.totalElements = totalElements; }
    public List<P> getItems() { return items; }
    public void setItems(List<P> items) { this.items = items; }

    public static <T, R> PageResponse<R> from(Page<T> page, Function<T, R> mapper) {
        List<R> items = page.stream().map(mapper).collect(Collectors.toList());
        return new PageResponse<>(page.getNumber(), page.getSize(), page.getTotalPages(), page.getTotalElements(), items);
    }
}
