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

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class InventoryController {
  private final File FILE_PATH = new File("src/data/stock_data.csv");


  private final CrudProductService productService;

  @GetMapping("/all")
  public ResponseEntity<List<ProductDTO>> getAllProducts() {
    List<ProductDTO> allProducts = this.productService.getAllProducts();
    return ResponseEntity.ok(allProducts);
  }

  @GetMapping("/id/{id}")
  public ResponseEntity<ProductDTO> getProductById(@PathVariable Integer id) {
    ProductDTO product = this.productService.getProductById(id);
    return ResponseEntity.ok(product);
  }

  @GetMapping("/name/{name}")
  public ResponseEntity<List<ProductDTO>> getProductByName(@PathVariable String name) {
    List<ProductDTO> product = this.productService.getProductByName(name);
    return ResponseEntity.ok(product);
  }

  @GetMapping("/category/{category}")
  public ResponseEntity<List<ProductDTO>> getProductByCategory(@PathVariable String category) {
    List<ProductDTO> product = this.productService.getProductByCategory(category);
    return ResponseEntity.ok(product);
  }

  @PostMapping
  public ResponseEntity<Integer> createProduct(@Valid @RequestBody ProductDTO data, UriComponentsBuilder uriComponentsBuilder) {
    Integer id = productService.addProduct(data);
    var uri = uriComponentsBuilder.path("/product/id/{id}").buildAndExpand(id).toUri();
    return ResponseEntity.created(uri).body(id);
  }

  @PutMapping
  public ResponseEntity<ProductDTO> updateProduct(@Valid @RequestBody ProductDTO updatedProduct) {
    return ResponseEntity.ok(productService.updateProduct(updatedProduct));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
    productService.deleteProductId(id);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/all")
  public ResponseEntity<Void> deleteAllProduct() {
    productService.deleteAllBD();
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/csv")
  public ResponseEntity<String> saveProductFromCsv() throws IOException {
    String message = productService.readProductsFromCsv(FILE_PATH.toPath());
    return ResponseEntity.ok(message);
  }
}
