package com.InventoryManagementSystem.controller;

import com.InventoryManagementSystem.service.StockReportingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@RestController
@RequestMapping("/stock")
@RequiredArgsConstructor
public class StockReportController {
    private final StockReportingService stockReportingService;

    /**
     * Generates a stock report and returns the URI of the generated report.
     *
     * @param builder UriComponentsBuilder for building the URI.
     * @return A ResponseEntity containing the URI of the generated stock report.
     * @throws IOException if there is an issue generating the report.
     */

    @GetMapping("/report")
    public ResponseEntity<?> generateStockReport(UriComponentsBuilder builder) throws IOException {
        stockReportingService.generateStockReport();
        var uri = builder.path("/stock/report").toUriString();
        return ResponseEntity.ok(uri);
    }

    /**
     * Generates a report of expiring products and returns the URI of the generated report.
     *
     * @param builder UriComponentsBuilder for building the URI.
     * @return A ResponseEntity containing the URI of the generated report for expiring products.
     * @throws IOException if there is an issue generating the report.
     */

    @GetMapping("/report/expired")
    public ResponseEntity<?> generateStockReportCsv(UriComponentsBuilder builder) throws IOException {
        stockReportingService.generateExpiringProductReport();
        var uri = builder.path("/stock/report/csv").toUriString();
        return ResponseEntity.ok(uri);
    }

    /**
     * Generates a stock report by category and returns the URI of the generated report.
     *
     * @param category The category of products for the report.
     * @param builder  UriComponentsBuilder for building the URI.
     * @return A ResponseEntity containing the URI of the generated category-specific report.
     * @throws IOException if there is an issue generating the report.
     */

    @GetMapping("/report/category/{category}")
    public ResponseEntity<?> generateStockReportCsv(@PathVariable String category, UriComponentsBuilder builder) throws IOException {
        stockReportingService.generateProductReportByCategory(category);
        var uri = builder.path("/stock/report/csv").toUriString();
        return ResponseEntity.ok(uri);
    }
}
