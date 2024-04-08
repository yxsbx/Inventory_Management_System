package com.InventoryManagementSystem.service;

import com.InventoryManagementSystem.dto.ProductDTO;
import com.InventoryManagementSystem.model.Product;
import com.InventoryManagementSystem.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
@AllArgsConstructor
public class StockProductVerifyService {

    private final CrudProductService crudProductService;
    private final ProductRepository productRepository;



    public void applyDiscountToProducts(int days, int discountPercentage) {

        List<ProductDTO> nearExpiryProductsFromCrud = getNearExpiryProductsFromCrud(days);

        nearExpiryProductsFromCrud.forEach(item -> {
            Product product = new Product(item);
            product.setPriceInCents(product.getPriceInCents() - (product.getPriceInCents() * discountPercentage / 100));
            product.setActiveDiscount(true);
            productRepository.save(product);
        });
    }


    private List<ProductDTO> getNearExpiryProductsFromCrud(int days) {
        return crudProductService.getAllProducts()
                .stream()
                .filter(item -> item.expiryDate().isBefore(LocalDate.now().plusDays(days)) && !item.activeDiscount())
                .toList();
    }

}
