package com.InventoryManagementSystem.controller;

import com.InventoryManagementSystem.service.StockReportingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@RestController
@RequestMapping("/stock")
@RequiredArgsConstructor
public class StockController {
  private final StockReportingService stockReportingService;

  @GetMapping("/report")
  public ResponseEntity<?> generateStockReport(UriComponentsBuilder builder) throws IOException {
    stockReportingService.writeStockReportFile();
    var uri = builder.path("/stock/report").toUriString();
    return ResponseEntity.ok(uri);
  }
}
