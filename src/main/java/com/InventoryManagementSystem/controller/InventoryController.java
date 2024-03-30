package com.InventoryManagementSystem.controller;

import com.InventoryManagementSystem.dto.ProductDTO;
import com.InventoryManagementSystem.model.enums.ProductCategory;
import com.InventoryManagementSystem.service.CrudProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/product")
public class InventoryController {

  private final CrudProductService productService;

  public InventoryController(CrudProductService productService) {
    this.productService = productService;
  }

  @GetMapping("/all")
  public ResponseEntity<List<ProductDTO>> getAllProducts() {
    List<ProductDTO> allProducts = productService.getAllProducts();
    return ResponseEntity.ok(allProducts);
  }
  @GetMapping("/id/{id}")
  public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
    ProductDTO product = productService.getProductById(id);
    return ResponseEntity.ok(product);
  }
  @GetMapping("/name/{name}")
  public ResponseEntity<List<ProductDTO>> getProductByName(@PathVariable String name) {
    List<ProductDTO> product = productService.getProductByName(name);
    return ResponseEntity.ok(product);
  }
  @GetMapping("/category/{category}")
  public ResponseEntity<List<ProductDTO>> getProductByCategory(@PathVariable ProductCategory category) {
    List<ProductDTO> product = productService.getProductByCategory(category);
    return ResponseEntity.ok(product);
  }

  @PostMapping
  public ResponseEntity<Void> createProduct(@Valid @RequestBody ProductDTO data) {
    productService.addProduct(data);
    return ResponseEntity.ok().build();
  }

  @PutMapping
  public ResponseEntity<ProductDTO> updateProduct(@Valid @RequestBody ProductDTO updatedProduct) {
    return ResponseEntity.ok(productService.updateProduct(updatedProduct));
  }

  @DeleteMapping( "/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
    productService.deleteProductId(id);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping( "/all")
  public ResponseEntity<Void> deleteProduct() {
    productService.deleteAllBD();
    return ResponseEntity.noContent().build();
  }
  @GetMapping("/csv")
  public ResponseEntity<Void> saveProductFromCsv(){
    File csvFilePath = new File("src/data/stock_data.csv");
    try {
      productService.saveProductFromCsv(csvFilePath);
    }catch (Exception e){
      e.printStackTrace();;//TODO: Não esquecer de arrumar
    }
    return ResponseEntity.ok().build();
  }
}
