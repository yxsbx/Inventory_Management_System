package com.InventoryManagementSystem.dto;

import com.InventoryManagementSystem.model.Product;
import com.InventoryManagementSystem.model.enums.ProductCategory;

import java.time.LocalDate;

/**
 * Data transfer object for Product details.
 */

public record ProductDTO(
        Integer productCode,
        ProductCategory category,
        String subcategory,
        String name,
        Integer stockQuantity,
        Integer priceInCents,
        String sizeOrLot,
        LocalDate expiryDate,
        boolean activeDiscount
) {

    /**
     * Constructs a ProductDTO from a Product entity.
     *
     * @param product The Product entity.
     */


    public ProductDTO(Product product) {
        this(
                product.getProductCode(),
                product.getCategory(),
                product.getSubcategory(),
                product.getName(),
                product.getStockQuantity(),
                product.getPriceInCents(),
                product.getSizeOrLot(),
                product.getExpiryDate(),
                product.getActiveDiscount()
        );
    }
}