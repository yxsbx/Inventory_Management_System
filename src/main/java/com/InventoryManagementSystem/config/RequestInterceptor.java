package com.InventoryManagementSystem.config;

import com.InventoryManagementSystem.service.StockProductVerifyService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;


@AllArgsConstructor
public class RequestInterceptor implements HandlerInterceptor {
    private final StockProductVerifyService stockProductVerifyService;

    /**
     * Intercepts HTTP requests to apply a discount before the request is handled.
     *
     * @param request  The current HTTP request.
     * @param response The current HTTP response.
     * @param handler  The chosen handler to execute, for type and/or instance evaluation.
     * @return true to continue the request, false to abort.
     */

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        stockProductVerifyService.applyDiscountToProducts(5, 50);
        System.err.println("Estoque Atualizado");
        return true;
    }
}
