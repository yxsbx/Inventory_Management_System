package com.InventoryManagementSystem.config;

import com.InventoryManagementSystem.service.StockProductVerifyService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final StockProductVerifyService stockProductVerifyService;

    /**
     * Registers interceptors for pre- and post-processing of requests.
     *
     * @param registry Interceptor registry for adding interceptors.
     */

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestInterceptor(stockProductVerifyService));
    }
}
