/*package com.InventoryManagementSystem.service;

import com.InventoryManagementSystem.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InventoryService implements IInventoryService {

    private final ProductRepository productRepository;

    @Autowired
    public InventoryService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> readAll() {
        return productRepository.readAll();
    }

    @Override
    public Optional<Product> readById(String id) {
        return productRepository.readById(id);
    }

    @Override
    public void create(Product product) {
        productRepository.create(product);
    }

    @Override
    public void update(Product product) {
        productRepository.update(product);
    }

    @Override
    public void delete(String id) {
        productRepository.readById(id).ifPresent(productRepository::delete);
    }

    @Override
    public List<Product> findLowStockProducts() {
        return readAll().stream()
                .filter(p -> p.getQuantityInStock() < 5)
                .collect(Collectors.toList());
    }

    @Override
    public void sellProduct(String productId, int quantity) {
        readById(productId).ifPresent(product -> {
            if (product.getQuantityInStock() >= quantity) {
                product.setQuantityInStock(product.getQuantityInStock() - quantity);
                update(product);
            } else {
                throw new IllegalStateException("Insufficient stock for product: " + productId);
            }
        });
    }

    @Override
    public List<Product> readProductsFromCSV(Path filePath) {
        return null;
    }

    public void displayLowStockProducts() {
        List<Product> lowStockProducts = findLowStockProducts();
        lowStockProducts.forEach(product ->
                System.out.println(product.getName() + " - Quantity: " + product.getQuantityInStock()));
    }

    @Override
    public void applyDiscountsToExpiringProducts() {
        List<Product> expiringProducts = readAll().stream()
                .collect(Collectors.toList());

        expiringProducts.forEach(product -> {
            product.setPrice(product.getPrice() * 0.8);
            update(product);
        });
    }
}*/