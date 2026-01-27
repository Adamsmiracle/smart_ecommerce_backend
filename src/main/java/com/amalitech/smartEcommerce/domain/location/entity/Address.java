package com.amalitech.smartEcommerce.domain.location.entity;

import com.amalitech.smartEcommerce.common.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@Entity
@Table(name = "address")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Address extends BaseEntity {


    @Size(max = 10, message = "Unit number cannot exceed 10 characters")
    @Pattern(regexp = "^[A-Z0-9\\-#]*$", message = "Unit number can only contain letters, numbers, hyphens, and #")
    @Column(name = "unit_number")
    private String unitNumber;

    @NotBlank(message = "Street number is required")
    @Size(max = 20, message = "Street number cannot exceed 20 characters")
    @Pattern(regexp = "^[0-9A-Za-z\\s\\-#/]*$", message = "Invalid street number format")
    @Column(name = "street_number")
    private String streetNumber;

    @NotBlank(message = "Address line is required")
    @Size(min = 5, max = 255, message = "Address line must be between 5 and 255 characters")
    @Column(name = "address_line")
    private String addressLine;

    @NotBlank(message = "City is required")
    @Size(min = 2, max = 100, message = "City must be between 2 and 100 characters")
    @Pattern(regexp = "^[A-Za-z\\s\\-'.]+$", message = "City can only contain letters, spaces, hyphens, apostrophes, and periods")
    @Column(name = "city", nullable = false)
    private String city;

    @NotBlank(message = "Region/State is required")
    @Size(min = 2, max = 100, message = "Region must be between 2 and 100 characters")
    @Column(name = "region", nullable = false)
    private String region;

    @NotNull(message = "Country is required")
    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

}