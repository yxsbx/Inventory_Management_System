package com.InventoryManagementSystem.service;

import com.InventoryManagementSystem.dto.ProductDTO;
import com.InventoryManagementSystem.model.Product;
import com.InventoryManagementSystem.model.enums.ProductCategory;
import com.InventoryManagementSystem.model.exceptions.ProductIdNotFoundException;
import com.InventoryManagementSystem.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for CRUD operations on products.
 */

@Service
@RequiredArgsConstructor
public class CrudProductService {

    private final ProductRepository productRepository;

    /**
     * Adds a product to the inventory.
     *
     * @param product The ProductDTO with the product data.
     * @return The product code of the added product.
     */

    public Integer addProduct(ProductDTO product) {
        productRepository.save(new Product(product));
        return product.productCode();
    }

    /**
     * Retrieves all products from the inventory.
     *
     * @return A list of ProductDTOs for all products.
     */

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream().map(ProductDTO::new).toList();
    }

    /**
     * Updates a product in the inventory.
     * @param product The ProductDTO with updated product data.
     * @return A ProductDTO of the updated product.
     */

    public ProductDTO updateProduct(ProductDTO product) {
        if (!productRepository.existsById(product.productCode())) {
            throw new ProductIdNotFoundException(String.valueOf(product.productCode()));
        }
        return new ProductDTO(productRepository.save(new Product(product)));
    }

    /**
     * Deletes a product by its ID.
     * @param id The product code of the product to delete.
     */

    public void deleteProductId(Integer id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ProductIdNotFoundException(String.valueOf(id))
        );
        productRepository.delete(product);
    }

    /**
     * Retrieves a product by its ID.
     * @param id The product code of the product.
     * @return A ProductDTO of the retrieved product.
     */

    public ProductDTO getProductById(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductIdNotFoundException(String.valueOf(id)));
        return new ProductDTO(product);
    }

    /**
     * Retrieves products by their name.
     * @param name The name of the products to retrieve.
     * @return A list of ProductDTOs of the retrieved products.
     */

    public List<ProductDTO> getProductByName(String name) {
        return productRepository.findAll()
                .stream()
                .filter(product -> product.getName().toLowerCase().contains(name.toLowerCase()))
                .map(ProductDTO::new).toList();
    }

    /**
     * Retrieves products by their category.
     * @param category The category of the products to retrieve.
     * @return A list of ProductDTOs of the retrieved products.
     */

    public List<ProductDTO> getProductByCategory(String category) {
        return productRepository.findAll()
                .stream()
                .filter(product -> String.valueOf(product.getCategory()).contains((category.toUpperCase())))
                .map(ProductDTO::new).toList();
    }

    /**
     * Deletes all products from the inventory.
     */

    public void deleteAllBD() {
        productRepository.deleteAll();
    }

    /**
     * Reads products from a CSV file and adds them to the inventory.
     * @param csvFilePath The path to the CSV file.
     * @return A success message after importing products.
     * @throws IOException If an error occurs during file reading.
     */

    public String readProductsFromCsv(Path csvFilePath) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(csvFilePath)) {
            List<Product> products = reader.lines()
                    .skip(1)
                    .map(this::stringToObject)
                    .collect(Collectors.toList());

            productRepository.saveAll(products);
            return "Produtos importados com sucesso!";
        }
    }

    /**
     * Converts a CSV string to a Product object.
     * @param s The CSV string representing a product.
     * @return A Product object.
     */

    private Product stringToObject(String s) {
        String[] parts = s.split(",");
        return new Product(
                Integer.parseInt(parts[0]), // productCode
                ProductCategory.valueOf(parts[1].trim().toUpperCase()), // category
                parts[2].trim(), // subcategory
                parts[3].trim(), // name
                Integer.parseInt(parts[4].trim()), // stockQuantity
                Integer.parseInt(parts[5].trim()), // priceInCents
                parts[6].trim(), // sizeOrLot
                LocalDate.parse(parts[7].trim()), // expiryDate
                false
        );
    }
}


