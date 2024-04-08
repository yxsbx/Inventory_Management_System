package com.InventoryManagementSystem.model;

import com.InventoryManagementSystem.dto.ProductDTO;
import com.InventoryManagementSystem.model.enums.ProductCategory;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * Entity class representing a product in the inventory.
 */

@Table(name = "product")
@Entity(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productCode;
    private ProductCategory category;
    private String subcategory;
    private String name;
    private Integer stockQuantity;
    private Integer priceInCents;
    private String sizeOrLot;
    private LocalDate expiryDate;
    @Column(name = "active_discount")
    private Boolean activeDiscount;

    /**
     * Constructs a Product from a ProductDTO.
     *
     * @param product A ProductDTO containing product data.
     */

    public Product(ProductDTO product) {
        this(
                product.productCode(),
                product.category(),
                product.subcategory(),
                product.name(),
                product.stockQuantity(),
                product.priceInCents(),
                product.sizeOrLot(),
                product.expiryDate(),
                false
        );
    }
}