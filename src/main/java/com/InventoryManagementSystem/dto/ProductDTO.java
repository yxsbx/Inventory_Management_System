package com.InventoryManagementSystem.dto;

import com.InventoryManagementSystem.model.Product;
import com.InventoryManagementSystem.model.enums.ProductCategory;

import java.time.LocalDate;

public record ProductDTO(
        Integer productCode,
        ProductCategory category,
        String subcategory,
        String name,
        Integer stockQuantity,
        Integer priceInCents,
        String sizeOrLot,
        LocalDate expiryDate
) {


  public ProductDTO(Product product) {
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