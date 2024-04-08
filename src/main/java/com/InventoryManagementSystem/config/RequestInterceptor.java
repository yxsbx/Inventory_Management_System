package com.InventoryManagementSystem.config;

import com.InventoryManagementSystem.service.StockProductVerifyService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;


@AllArgsConstructor
public class RequestInterceptor implements HandlerInterceptor {
  private final StockProductVerifyService stockProductVerifyService;
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    stockProductVerifyService.applyDiscountToProducts(5, 10);
    System.err.println("Estoque Atualizado");
    return true;
  }
}
