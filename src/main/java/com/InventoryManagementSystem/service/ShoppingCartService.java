package com.InventoryManagementSystem.service;

import com.InventoryManagementSystem.dto.ProductDTO;
import com.InventoryManagementSystem.model.Product;
import com.InventoryManagementSystem.model.exceptions.ProductIdNotFoundException;
import com.InventoryManagementSystem.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShoppingCartService {

  private final List<Product> shoppingCart = new ArrayList<>();
  private ProductRepository productRepository;
  private CrudProductService crudProductService;

  public synchronized void addToCart(Integer productId, int stockQuantity) {
    Product item = this.productRepository.findById(productId).orElseThrow(
            () -> new ProductIdNotFoundException("Item not found.")
    );

    if (stockQuantity < 0 && item.getStockQuantity() < 0) {
      throw new IllegalArgumentException("Stock quantity must be positive.");
    }

    if (isProductInCart(item.getProductCode())) {
      Product itemInCart = getProductInCart(item.getProductCode());
      itemInCart.setStockQuantity(itemInCart.getStockQuantity() + stockQuantity);
    } else {
      shoppingCart.add(item);
    }
  }
  public void removeFromCart(Integer productCode) {
    shoppingCart.removeIf(item -> item.getProductCode().equals(productCode));
  }

  public void clearCart() {
    shoppingCart.clear();
  }

  public String checkout() {
    StringBuilder sb = new StringBuilder();
    sb.append("Items in your cart:\n");
    for (Product item : shoppingCart) {
      sb.append(item.getName()).append(" - Quantity: ").append(item.getStockQuantity()).append("\n");
    }
    sb.append("Checkout successful");
    return sb.toString();
  }

  public List<ProductDTO> getShoppingCart(){
    return shoppingCart.stream().map(ProductDTO::new).toList();
  }

  private boolean isProductInCart(Integer productCode) {
    return shoppingCart.stream().anyMatch(item -> item.getProductCode().equals(productCode));
  }

  private Product getProductInCart(Integer productCode) {
    return shoppingCart.stream().filter(item -> item.getProductCode().equals(productCode)).findFirst().get();
  }




  
}
