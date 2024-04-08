package com.InventoryManagementSystem.controller;

import com.InventoryManagementSystem.dto.ProductDTO;
import com.InventoryManagementSystem.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling shopping cart-related requests.
 */

@RestController
@RequestMapping("/shopping")
@RequiredArgsConstructor
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    /**
     * Retrieves all products in the shopping cart.
     *
     * @return A ResponseEntity containing a list of ProductDTO objects in the cart.
     */

    @GetMapping("/cart")
    public ResponseEntity<List<ProductDTO>> getShoppingCart() {
        List<ProductDTO> shoppingCart = shoppingCartService.getShoppingCart();
        return ResponseEntity.ok(shoppingCart);
    }

    /**
     * Adds a product to the shopping cart.
     *
     * @param productDTO    The ProductDTO of the product to add.
     * @param stockQuantity The quantity of the product to add.
     * @return A ResponseEntity with ok status.
     */

    @PostMapping("/cart/add")
    public ResponseEntity<Void> addToCart(@RequestBody ProductDTO productDTO, @RequestParam int stockQuantity) {
        shoppingCartService.addToCart(productDTO, stockQuantity);
        return ResponseEntity.ok().build();
    }

    /**
     * Removes a product from the shopping cart.
     *
     * @param id The ID of the product to remove.
     * @return A ResponseEntity with ok status.
     */

    @DeleteMapping("/cart/remove/{id}")
    public ResponseEntity<Void> removeFromCart(@PathVariable Integer id) {
        shoppingCartService.removeFromCart(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Clears all products from the shopping cart.
     *
     * @return A ResponseEntity with ok status.
     */

    @DeleteMapping("/cart/clear")
    public ResponseEntity<Void> clearCart() {
        shoppingCartService.clearCart();
        return ResponseEntity.ok().build();
    }

    /**
     * Processes the checkout of the products in the cart.
     *
     * @return A ResponseEntity containing the checkout message.
     */

    @GetMapping("/checkout")
    public ResponseEntity<String> checkout() {
        String message = shoppingCartService.checkout();
        return ResponseEntity.ok(message);
    }
}
