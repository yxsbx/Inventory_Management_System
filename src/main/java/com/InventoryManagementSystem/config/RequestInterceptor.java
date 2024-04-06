package com.InventoryManagementSystem.config;

import com.InventoryManagementSystem.service.StockProductVerifyService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor

public class RequestInterceptor implements HandlerInterceptor {
  private final StockProductVerifyService stockProductVerifyService;
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    stockProductVerifyService.applyDiscountToProducts(5, 10);
    System.err.println("Estoque Atualizado");
    return true;
  }
}
