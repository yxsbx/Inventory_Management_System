/*package com.InventoryManagementSystem.service;

import com.InventoryManagementSystem.model.Product;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public interface IInventoryService {
    List<Product> readProductsFromCSV(Path filePath);
    void displayLowStockProducts();
    void applyDiscountsToExpiringProducts();

    List<Product> readAll();

    Optional<Product> readById(String id);

    void create(Product product);

    void update(Product product);

    void delete(String id);

    List<Product> findLowStockProducts();

    void sellProduct(String productId, int quantity)
}*/