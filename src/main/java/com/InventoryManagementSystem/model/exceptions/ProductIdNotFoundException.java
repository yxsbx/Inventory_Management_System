package com.InventoryManagementSystem.model.exceptions;

import org.springframework.http.ResponseEntity;

public class ProductIdNotFoundException extends RuntimeException {

  public ProductIdNotFoundException (String message) {
    super(message);
  }
}
