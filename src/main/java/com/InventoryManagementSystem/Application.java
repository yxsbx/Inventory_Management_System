package com.InventoryManagementSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The entry point of the Inventory Management System application.
 */

@SpringBootApplication
public class Application {

    /**
     * Main method that starts the Spring Boot application.
     * @param args Command line arguments passed to the application.
     */

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}