package com.InventoryManagementSystem.model.exceptions;

public class ProductIdNotFoundException extends RuntimeException {

  public ProductIdNotFoundException (String message) {
    super(message);
  }
}
