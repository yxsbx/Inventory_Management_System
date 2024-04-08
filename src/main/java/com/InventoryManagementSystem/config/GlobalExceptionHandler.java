package com.InventoryManagementSystem.config;

import com.InventoryManagementSystem.model.exceptions.ProductIdNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles exceptions for non-existing product IDs.
     *
     * @param e The exception that was thrown when a product ID was not found.
     * @return A ResponseEntity with a 404 status and error details in the ETag header.
     */


    @ExceptionHandler(ProductIdNotFoundException.class)
    public ResponseEntity<?> handleException(ProductIdNotFoundException e) {
        return ResponseEntity.notFound().eTag("Product not found with code: " + e.getMessage()).build();
    }

    /**
     * Handles IO exceptions that occur when importing products from a file.
     *
     * @param e The IOException that was caught during file processing.
     * @return A ResponseEntity with a 404 status and error details in the ETag header.
     */

    @ExceptionHandler(IOException.class)
    public ResponseEntity<?> handleException(IOException e) {
        return ResponseEntity.notFound().eTag("Error when importing products from file: " + e.getMessage()).build();
    }
}
