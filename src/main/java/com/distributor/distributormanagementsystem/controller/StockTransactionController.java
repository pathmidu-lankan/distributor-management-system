package com.distributor.distributormanagementsystem.controller;

import com.distributor.distributormanagementsystem.entity.StockTransaction;
import com.distributor.distributormanagementsystem.service.StockTransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock-transactions")
public class StockTransactionController {

    private final StockTransactionService stockTransactionService;

    public StockTransactionController(StockTransactionService stockTransactionService) {
        this.stockTransactionService = stockTransactionService;
    }

    @PostMapping
    public StockTransaction addTransaction(
            @RequestBody StockTransaction transaction) {

        return stockTransactionService.addTransaction(transaction);
    }

    @GetMapping
    public List<StockTransaction> getAllTransactions() {
        return stockTransactionService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockTransaction> getTransactionById(
            @PathVariable Long id) {

        return stockTransactionService.getTransactionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(
            @PathVariable Long id) {

        stockTransactionService.deleteTransaction(id);

        return ResponseEntity.noContent().build();
    }
}