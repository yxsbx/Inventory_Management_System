package com.InventoryManagementSystem.config;

import com.InventoryManagementSystem.model.exceptions.ProductIdNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(ProductIdNotFoundException.class)
  public ResponseEntity<?> handleException(ProductIdNotFoundException e) {
    return ResponseEntity.notFound().eTag("Product not found with code: " + e.getMessage()).build();
  }
  
  @ExceptionHandler(IOException.class)
  public ResponseEntity<?> handleException(IOException e) {
    return ResponseEntity.notFound().eTag("Error when importing products from file: " + e.getMessage()).build();
  }
}
