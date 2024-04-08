package com.InventoryManagementSystem.repository;

import com.InventoryManagementSystem.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA repository for Product entities.
 */


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}