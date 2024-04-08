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
@Service
@AllArgsConstructor
public class StockReportingService {
  private final CrudProductService productService;

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

  public void generateProductReportByCategory(String category) throws IOException {
    List<Product> ProductsByCategory = productService.getProductByCategory(category)
            .stream()
            .map(Product::new)
            .toList();

    writeStockReportFile(
            ProductsByCategory,
            generatereportFilePath("ProductsReportsByCategory"+category.trim())
    );
  }

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

  private Path generatereportFilePath(String directoryName) throws IOException {
    String reportDestinationPath = "src/data/reports/" + directoryName;

    Path reportsDirectory = Paths.get(reportDestinationPath);

    if (!Files.exists(reportsDirectory)) {
      Files.createDirectories(reportsDirectory);
    }

    String reportFileName = generateReportFileName();

    return reportsDirectory.resolve(reportFileName);
  }

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
