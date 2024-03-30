package com.InventoryManagementSystem.model;

import com.InventoryManagementSystem.dto.ProductDTO;
import com.InventoryManagementSystem.model.enums.ProductCategory;
import jakarta.persistence.*;
import lombok.*;

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
  private Long productCode;
  private ProductCategory category;
  private String subcategory;
  private String name;
  private Integer stockQuantity;
  private Integer priceInCents;
  private String sizeOrLot;
  private String expiryDate;

  public Product(ProductDTO product) {
    this(
            product.productCode(),
            product.category(),
            product.subcategory(),
            product.name(),
            product.stockQuantity(),
            product.priceInCents(),
            product.sizeOrLot(),
            product.expiryDate()
    );
  }
}