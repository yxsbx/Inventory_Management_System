package com.InventoryManagementSystem.repository;

import com.InventoryManagementSystem.model.Product;
import com.InventoryManagementSystem.model.enums.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IProductRepository extends JpaRepository<Product, String> {
    Optional<List<Product>> findProductsByName(String name);
    Optional<List<Product>> findProductsByCategory(ProductCategory category);

}