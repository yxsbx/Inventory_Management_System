package com.InventoryManagementSystem.controller;

import com.InventoryManagementSystem.dto.ProductDTO;
import com.InventoryManagementSystem.service.CrudProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Controller for handling inventory-related requests.
 */

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class InventoryController {
    private final File FILE_PATH = new File("src/data/stock_data.csv");
    private final CrudProductService productService;

    /**
     * Retrieves all products from the inventory.
     *
     * @return A ResponseEntity containing a list of all ProductDTO objects.
     */

    @GetMapping("/all")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> allProducts = this.productService.getAllProducts();
        return ResponseEntity.ok(allProducts);
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param id The ID of the product.
     * @return A ResponseEntity containing the ProductDTO.
     */

    @GetMapping("/id/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Integer id) {
        ProductDTO product = this.productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    /**
     * Retrieves products by their name.
     *
     * @param name The name of the products.
     * @return A ResponseEntity containing a list of ProductDTOs.
     */

    @GetMapping("/name/{name}")
    public ResponseEntity<List<ProductDTO>> getProductByName(@PathVariable String name) {
        List<ProductDTO> product = this.productService.getProductByName(name);
        return ResponseEntity.ok(product);
    }

    /**
     * Retrieves products by their category.
     *
     * @param category The category of the products.
     * @return A ResponseEntity containing a list of ProductDTOs.
     */

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ProductDTO>> getProductByCategory(@PathVariable String category) {
        List<ProductDTO> product = this.productService.getProductByCategory(category);
        return ResponseEntity.ok(product);
    }

    /**
     * Creates a new product in the inventory.
     *
     * @param data                 The ProductDTO containing the product data.
     * @param uriComponentsBuilder URI components builder for response location header.
     * @return A ResponseEntity containing the ID of the created product.
     */

    @PostMapping
    public ResponseEntity<Integer> createProduct(@Valid @RequestBody ProductDTO data, UriComponentsBuilder uriComponentsBuilder) {
        Integer id = productService.addProduct(data);
        var uri = uriComponentsBuilder.path("/product/id/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).body(id);
    }

    /**
     * Updates an existing product.
     *
     * @param updatedProduct The ProductDTO containing the updated product data.
     * @return A ResponseEntity containing the updated ProductDTO.
     */

    @PutMapping
    public ResponseEntity<ProductDTO> updateProduct(@Valid @RequestBody ProductDTO updatedProduct) {
        return ResponseEntity.ok(productService.updateProduct(updatedProduct));
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id The ID of the product to delete.
     * @return A ResponseEntity with no content.
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        productService.deleteProductId(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Deletes all products from the inventory.
     *
     * @return A ResponseEntity with no content.
     */

    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAllProduct() {
        productService.deleteAllBD();
        return ResponseEntity.noContent().build();
    }

    /**
     * Imports products from a CSV file and saves them in the inventory.
     *
     * @return A ResponseEntity with the operation message.
     * @throws IOException If reading from the CSV file fails.
     */

    @GetMapping("/csv")
    public ResponseEntity<String> saveProductFromCsv() throws IOException {
        String message = productService.readProductsFromCsv(FILE_PATH.toPath());
        return ResponseEntity.ok(message);
    }
}
