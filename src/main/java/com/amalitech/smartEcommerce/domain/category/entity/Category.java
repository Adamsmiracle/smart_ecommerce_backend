package com.amalitech.smartEcommerce.domain.category.entity;

import com.amalitech.smartEcommerce.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Table(name = "product_category")
public class Category extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "parent_category_id")
    private Category parentCategory;

    @Size(min = 2, message = "category name must be more than one character")
    @Column(name = "category_name", nullable = false)
    private String name;

}
