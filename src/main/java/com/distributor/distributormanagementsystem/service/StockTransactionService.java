package com.distributor.distributormanagementsystem.service;

import com.distributor.distributormanagementsystem.entity.StockTransaction;
import com.distributor.distributormanagementsystem.repository.StockTransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockTransactionService {

    private final StockTransactionRepository stockTransactionRepository;

    public StockTransactionService(StockTransactionRepository stockTransactionRepository) {
        this.stockTransactionRepository = stockTransactionRepository;
    }

    public StockTransaction addTransaction(StockTransaction transaction) {
        return stockTransactionRepository.save(transaction);
    }

    public List<StockTransaction> getAllTransactions() {
        return stockTransactionRepository.findAll();
    }

    public Optional<StockTransaction> getTransactionById(Long id) {
        return stockTransactionRepository.findById(id);
    }

    public void deleteTransaction(Long id) {
        stockTransactionRepository.deleteById(id);
    }
}