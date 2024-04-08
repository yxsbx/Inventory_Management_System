package com.InventoryManagementSystem.controller;

import com.InventoryManagementSystem.dto.ProductDTO;
import com.InventoryManagementSystem.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shopping")
@RequiredArgsConstructor
public class ShoppingCartController {

  private final ShoppingCartService shoppingCartService;

  @GetMapping("/cart")
  public ResponseEntity<List<ProductDTO>> getShoppingCart() {
    List<ProductDTO> shoppingCart = shoppingCartService.getShoppingCart();
    return ResponseEntity.ok(shoppingCart);
  }

  @GetMapping("/cart/add/{productId}/{Quantity}")
  public ResponseEntity<Void> addToCart(@PathVariable Integer productId, @PathVariable int Quantity) {
    shoppingCartService.addToCart(productId, Quantity);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/cart/remove/{id}")
  public ResponseEntity<Void> removeFromCart(@PathVariable Integer id) {
    shoppingCartService.removeFromCart(id);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/cart/clear")
  public ResponseEntity<Void> clearCart() {
    shoppingCartService.clearCart();
    return ResponseEntity.ok().build();
  }

  @GetMapping("/checkout")
  public ResponseEntity<String> checkout() {
    String message = shoppingCartService.checkout();
    return ResponseEntity.ok(message);
  }
}
