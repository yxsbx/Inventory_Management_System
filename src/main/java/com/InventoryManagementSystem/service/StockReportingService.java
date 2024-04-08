package com.InventoryManagementSystem.service;

import com.InventoryManagementSystem.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Service for generating reports related to stock and product expiration.
 */

@Service
@AllArgsConstructor
public class StockReportingService {
    private final CrudProductService productService;

    /**
     * Generates a stock report for all products.
     * @throws IOException If an error occurs during report generation.
     */

    public void generateStockReport() throws IOException {
        List<Product> listToGenerateReport = productService.getAllProducts()
                .stream()
                .map(Product::new)
                .toList();

        writeStockReportFile(
                listToGenerateReport,
                generatereportFilePath("StockReports")
        );
    }

    /**
     * Generates a report for products filtered by category.
     * @param category of products to report on.
     * @throws IOException If an error occurs during report generation.
     */

    public void generateProductReportByCategory(String category) throws IOException {
        List<Product> ProductsByCategory = productService.getProductByCategory(category)
                .stream()
                .map(Product::new)
                .toList();

        writeStockReportFile(
                ProductsByCategory,
                generatereportFilePath("ProductsReportsByCategory" + category.trim())
        );
    }

    /**
     * Generates a report for expired products.
     * @throws IOException If an error occurs during report generation.
     */

    public void generateExpiringProductReport() throws IOException {
        List<Product> ExpiredProducts = productService.getAllProducts()
                .stream()
                .filter(item -> item.expiryDate().isBefore(LocalDate.now()))
                .toList().stream().map(Product::new).toList();

        writeStockReportFile(
                ExpiredProducts,
                generatereportFilePath("ExpiredProductsReports")
        );
    }

    /**
     * Generates the file path for storing the report.
     * @param directoryName The name of the directory to store the report in.
     * @return The path of the generated report file.
     * @throws IOException if there is an issue creating the directory.
     */

    private Path generatereportFilePath(String directoryName) throws IOException {
        String reportDestinationPath = "src/data/reports/" + directoryName;

        Path reportsDirectory = Paths.get(reportDestinationPath);

        if (!Files.exists(reportsDirectory)) {
            Files.createDirectories(reportsDirectory);
        }

        String reportFileName = generateReportFileName();

        return reportsDirectory.resolve(reportFileName);
    }

    /**
     * Writes the stock report file with the given product data.
     * @param listToGenerateReport The list of products to include in the report.
     * @param reportFilePath The file path to write the report to.
     * @throws IOException if there is an issue with file writing.
     */

    private void writeStockReportFile(List<Product> listToGenerateReport, Path reportFilePath) throws IOException {
        List<String> lines =
                listToGenerateReport
                        .stream()
                        .map(this::productToString)
                        .toList();

        Files.writeString(
                reportFilePath,
                String.join("\n", lines),
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING);

        System.err.println("Relatório gerado com sucesso: " + reportFilePath);
    }

    /**
     * Converts a product to a string format suitable for report generation.
     * @param product The product to convert.
     * @return A string representation of the product.
     */

    protected String productToString(Product product) {
        return String.join(",",
                String.valueOf(product.getProductCode()),
                product.getSubcategory(),
                product.getName(),
                String.valueOf(product.getStockQuantity()),
                String.valueOf(product.getPriceInCents() / 100),
                product.getSizeOrLot(),
                String.valueOf(product.getExpiryDate())
        );
    }

    private String generateReportFileName() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        return "report_" + now.format(formatter) + ".csv";
    }
}
