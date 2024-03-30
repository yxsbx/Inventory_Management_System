package com.InventoryManagementSystem.dto;

import com.InventoryManagementSystem.model.Product;
import com.InventoryManagementSystem.model.enums.ProductCategory;

import java.time.LocalDate;
import java.util.List;

public record ProductDTO(
        Long productCode,
        ProductCategory category,
        String subcategory,
        String name,
        Integer stockQuantity,
        Integer priceInCents,
        String sizeOrLot,
        String expiryDate
        ) {


    public ProductDTO(Product product){
        this(
                product.getProductCode(),
                product.getCategory(),
                product.getSubcategory(),
                product.getName(),
                product.getStockQuantity(),
                product.getPriceInCents(),
                product.getSizeOrLot(),
                product.getExpiryDate()
        );
    }

}