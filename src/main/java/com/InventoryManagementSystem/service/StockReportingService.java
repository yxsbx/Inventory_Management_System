package com.InventoryManagementSystem.service;

import com.InventoryManagementSystem.model.Product;
import com.InventoryManagementSystem.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
@Service
@AllArgsConstructor
public class StockReportingService {
  private final ProductRepository productRepository;

  public void writeStockReportFile() throws IOException {
     String reportDestinationPath = "src/data/reports";

    // Verificar e criar o diretório reports se não existir
    Path reportsDirectory = Paths.get(reportDestinationPath);
    if (!Files.exists(reportsDirectory)) {
      Files.createDirectories(reportsDirectory);
    }

    // Gerar um nome de arquivo personalizado com a data e hora atual
    String reportFileName = generateReportFileName();

    // Construir o caminho completo do arquivo
    Path reportFilePath = reportsDirectory.resolve(reportFileName);

    // Escrever o relatório no arquivo com o nome personalizado
    List<String> lines = productRepository
            .findAll()
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
