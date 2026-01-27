package com.amalitech.smartEcommerce.domain.location.entity;

import com.amalitech.smartEcommerce.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "country")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Country extends BaseEntity {

    @NotBlank(message = "Country name is required")
    @Size(min = 2, max = 100, message = "Country name must be between 2 and 100 characters")
    @Pattern(regexp = "^[A-Za-z\\s\\-'.()&]+$",
            message = "Country name can only contain letters, spaces, hyphens, apostrophes, periods, parentheses, and ampersands")
    @Column(name = "country_name", nullable = false, length = 100)
    private String countryName;

}

