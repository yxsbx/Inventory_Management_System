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

@Service
@RequiredArgsConstructor
public class CrudProductService {

  private final ProductRepository productRepository;

  public Integer addProduct(ProductDTO product) {
    productRepository.save(new Product(product));
    return product.productCode();
  }

  public List<ProductDTO> getAllProducts() {
    return productRepository.findAll().stream().map(ProductDTO::new).toList();
  }

  public ProductDTO updateProduct(ProductDTO product) {
    if (!productRepository.existsById(product.productCode())) {
      throw new ProductIdNotFoundException(String.valueOf(product.productCode()));
    }
    return new ProductDTO(productRepository.save(new Product(product)));
  }

  public void deleteProductId(Integer id) {
    Product product = productRepository.findById(id).orElseThrow(
            () -> new ProductIdNotFoundException(String.valueOf(id))
    );
    productRepository.delete(product);
  }

  public ProductDTO getProductById(Integer id) {
    Product product = productRepository.findById(id)
            .orElseThrow(() -> new ProductIdNotFoundException(String.valueOf(id)));
    return new ProductDTO(product);
  }

  public List<ProductDTO> getProductByName(String name) {
    return productRepository.findAll()
            .stream()
            .filter(product -> product.getName().toLowerCase().contains(name.toLowerCase()))
            .map(ProductDTO::new).toList();
  }

  public List<ProductDTO> getProductByCategory(String category) {
    return productRepository.findAll()
            .stream()
            .filter(product -> String.valueOf(product.getCategory()).contains((category.toUpperCase())))
                    .map(ProductDTO::new).toList();
  }

  public void deleteAllBD() {
    productRepository.deleteAll();
  }

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
            LocalDate.parse(parts[7].trim()) // expiryDate
    );
  }
}


