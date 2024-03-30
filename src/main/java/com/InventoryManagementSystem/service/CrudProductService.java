package com.InventoryManagementSystem.service;

import com.InventoryManagementSystem.dto.ProductDTO;
import com.InventoryManagementSystem.model.Product;
import com.InventoryManagementSystem.model.enums.ProductCategory;
import com.InventoryManagementSystem.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Stream;

@Service
public class CrudProductService {
  private final ProductRepository productRepository;

  public CrudProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public void addProduct(ProductDTO product) {
    productRepository.save(new Product(product));
  }

  public List<ProductDTO> getAllProducts() {
    return productRepository.findAll().stream().map(ProductDTO::new).toList();
  }

  public ProductDTO updateProduct(ProductDTO product) {
    return new ProductDTO(productRepository.save(new Product(product)));
  }

  public void deleteProductId(Long id) {
    Product product = productRepository.findById(id).get();
    productRepository.delete(product);
  }

  public ProductDTO getProductById(Long id) {
    return new ProductDTO(productRepository.findById(id).get());
  }

  public List<ProductDTO> getProductByName(String name) {
    return productRepository.findAll()
            .stream()
            .filter(product -> product.getSubcategory().equals(name))
            .map(ProductDTO::new).toList();
  }

  public List<ProductDTO> getProductByCategory(ProductCategory category) {
    return productRepository.findAll()
            .stream()
            .filter(product -> product.getCategory().equals(category))
            .map(ProductDTO::new).toList();
  }

  public void deleteAllBD() {
    productRepository.deleteAll();
  }

  public void saveProductFromCsv(File csvFilePath) {
    try (Stream<String> lines = Files.lines(csvFilePath.toPath())) {
      List<Product> products = lines
              .skip(1)
              .map(this::stringToObject)
              .toList();
      System.err.println("leu falta salva");
      productRepository.saveAll(products);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private Product stringToObject(String s) {
    String[] parts = s.split(",");
    return new Product(
            Long.parseLong(parts[0]), // productCode
            ProductCategory.valueOf(parts[1].trim().toUpperCase()), // category
            parts[2].trim(), // subcategory
            parts[3].trim(), // name
            Integer.parseInt(parts[4].trim()), // stockQuantity
            Integer.parseInt(parts[5].trim()), // priceInCents
            parts[6].trim(), // sizeOrLot
            parts[7].trim() // expiryDate
    );
  }
}


