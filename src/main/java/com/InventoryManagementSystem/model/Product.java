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
@EqualsAndHashCode(of = "id")
public class Product {
    @Id
    private String id;

    private String name;
    private ProductCategory category;
    private Integer quantityInStock;
    private Integer priceInCents;

    public Product(ProductDTO product) {
        this.id = product.id();
        this.name = product.name();
        this.category = product.category();
        this.quantityInStock = product.quantityInStock();
        this.priceInCents = product.priceInCents();
    }
}