package com.InventoryManagementSystem.service;

import com.InventoryManagementSystem.dto.ProductDTO;
import com.InventoryManagementSystem.model.Product;
import com.InventoryManagementSystem.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Service for applying discounts to products based on stock and expiry criteria.
 */

@Service
@AllArgsConstructor
public class StockProductVerifyService {

    private final CrudProductService crudProductService;
    private final ProductRepository productRepository;

    /**
     * Applies discounts to products that are nearing expiry. Discounts are calculated based
     * on the specified number of days to expiry and the discount percentage.
     * @param days Number of days to expiry to consider for discount application.
     * @param discountPercentage Percentage of the discount to be applied.
     */

    public void applyDiscountToProducts(int days, int discountPercentage) {

        List<ProductDTO> nearExpiryProductsFromCrud = getNearExpiryProductsFromCrud(days);

        nearExpiryProductsFromCrud.forEach(item -> {
            Product product = new Product(item);
            product.setPriceInCents(product.getPriceInCents() - (product.getPriceInCents() * discountPercentage / 100));
            product.setActiveDiscount(true);
            productRepository.save(product);
        });
    }

    /**
     * Retrieves a list of products that are close to expiry within the given number of days
     * and do not already have an active discount.
     * @param days Number of days to expiry to filter the products.
     * @return A list of ProductDTOs for products nearing expiry.
     */

    private List<ProductDTO> getNearExpiryProductsFromCrud(int days) {
        return crudProductService.getAllProducts()
                .stream()
                .filter(item -> item.expiryDate().isBefore(LocalDate.now().plusDays(days)) && !item.activeDiscount())
                .toList();
    }
}
