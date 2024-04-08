package com.InventoryManagementSystem.model.exceptions;

/**
 * Exception thrown when a product ID is not found in the inventory.
 */

public class ProductIdNotFoundException extends RuntimeException {

    /**
     * Constructor for ProductIdNotFoundException.
     *
     * @param message The detail message.
     */

    public ProductIdNotFoundException(String message) {
        super(message);
    }
}
