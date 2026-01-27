package com.amalitech.smartEcommerce.common.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import org.springframework.data.domain.Pageable;

public class PaginationRequestDTO {

    @Min(value = 0, message = "Page number must be 0 or greater")
    private int page = 0;

    @Min(value = 1, message = "Page size must be at least 1")
    @Max(value = 100, message = "Page size cannot exceed 100")
    private int size = 20;

//    Default sort field
    private String sortBy = "createdAt";
//    Default decending order sort direction
    private String sortedDirection = "DESC";


//    Converting to Pageable for repository
    public Pageable toPageable() {
        Sort sort = Sort.by(Sort.Direction.fromString(sortedDirection), sortBy);
        return PageRequest.of(page, size, sort);
    }
}
