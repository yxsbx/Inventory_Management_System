package com.InventoryManagementSystem.dto;

import com.InventoryManagementSystem.model.Product;
import com.InventoryManagementSystem.model.enums.ProductCategory;
import jakarta.validation.constraints.NotNull;

public record ProductDTO(
        String id,
        String name,
        ProductCategory category,
        Integer quantityInStock,

        Integer priceInCents) {

    public ProductDTO (Product product){
        this(
                product.getId(),
                product.getName(),
                product.getCategory(),
                product.getQuantityInStock(),
                product.getPriceInCents()
        );
    }
}