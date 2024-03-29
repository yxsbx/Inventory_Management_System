package com.InventoryManagementSystem.controller;

import com.InventoryManagementSystem.dto.ProductDTO;
import com.InventoryManagementSystem.model.Product;
import com.InventoryManagementSystem.repository.IProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class InventoryController {

    @Autowired
    private IProductRepository productRepository;

    @GetMapping
    public ResponseEntity getAllProducts() {
        var allProducts = productRepository.findAll();
        return ResponseEntity.ok(allProducts);
    }

    @PostMapping
    public ResponseEntity createProduct(@Valid @RequestBody ProductDTO data) {
        productRepository.save(new Product(data));
        return ResponseEntity.ok().build();
    }


    @PutMapping(path = "/{id}")
    @Transactional
    public ResponseEntity updateProduct(@PathVariable String id, @Valid @RequestBody ProductDTO data) {
        Optional<Product> productToBeUpdated = productRepository.findById(id);
        if (productToBeUpdated.isEmpty()) {
            throw new EntityNotFoundException("Product with id " + id + " not found");
        }
        productToBeUpdated.get().setName(data.name());
        productToBeUpdated.get().setCategory(data.category());
        productToBeUpdated.get().setQuantityInStock(data.quantityInStock());
        productToBeUpdated.get().setPriceInCents(data.priceInCents());

        return ResponseEntity.ok(new ProductDTO(productToBeUpdated.get()));
    }

    @DeleteMapping(path = "/{id}")
    @Transactional
    public ResponseEntity deleteProduct(@PathVariable String id) {
        Optional<Product> productToBeDeleted = productRepository.findById(id);
        if (productToBeDeleted.isEmpty()) {
            throw new EntityNotFoundException("Product with id " + id + " not found");
        }
        return ResponseEntity.noContent().build();
    }



}
