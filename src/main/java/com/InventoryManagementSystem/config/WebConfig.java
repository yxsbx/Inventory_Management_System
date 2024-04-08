package com.InventoryManagementSystem.config;

import com.InventoryManagementSystem.service.StockProductVerifyService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
  private  final StockProductVerifyService stockProductVerifyService;
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new RequestInterceptor(stockProductVerifyService ));
  }
}
