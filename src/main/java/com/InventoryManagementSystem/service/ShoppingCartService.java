package com.InventoryManagementSystem.service;

import com.InventoryManagementSystem.dto.ProductDTO;
import com.InventoryManagementSystem.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShoppingCartService {

  private final List<ProductDTO> shoppingCart = new ArrayList<>();

  public void addToCart(Product product, int stockQuantity) {
    if (stockQuantity <= 0) {
      throw new IllegalArgumentException("Stock quantity must be positive.");
    }

    Optional<ProductDTO> existingProduct = shoppingCart.stream()
            .filter(item -> item.productCode().equals(product.getProductCode()))
            .findFirst();

    if (existingProduct.isPresent()) {
      ProductDTO existingItem = existingProduct.get();
      ProductDTO updatedItem = existingItem.setStockQuantity(existingItem.stockQuantity() + stockQuantity);
      shoppingCart.remove(existingItem);
      shoppingCart.add(updatedItem);
    } else {
      ProductDTO newItem = new ProductDTO(product);
      newItem = newItem.setStockQuantity(stockQuantity);
      shoppingCart.add(newItem);
    }
  }

  public void removeFromCart(Integer productCode) {
    shoppingCart.removeIf(item -> item.productCode().equals(productCode));
  }

  public void clearCart() {
    shoppingCart.clear();
  }

  public String checkout() {
    StringBuilder sb = new StringBuilder();
    sb.append("Items in your cart:\n");
    for (ProductDTO item : shoppingCart) {
      sb.append(item.name()).append(" - Quantity: ").append(item.stockQuantity()).append("\n");
    }
    sb.append("Checkout successful");
    return sb.toString();
  }

  public List<ProductDTO> getShoppingCart() {
    return new ArrayList<>(shoppingCart);
  }
}
