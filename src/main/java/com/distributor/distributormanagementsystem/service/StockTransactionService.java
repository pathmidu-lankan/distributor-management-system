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

    public int getWarehouseStock(Long productId, Long warehouseId) {

        List<StockTransaction> transactions =
                stockTransactionRepository.findByProductIdAndWarehouseId(
                        productId,
                        warehouseId
                );

        int stock = 0;

        for (StockTransaction transaction : transactions) {

            String type = transaction.getTransactionType();

            if ("RECEIVE".equals(type)) {
                stock += transaction.getQuantity();

            } else if ("LOAD_TO_LORRY".equals(type)) {
                stock -= transaction.getQuantity();

            } else if ("RETURN".equals(type)) {
                stock += transaction.getQuantity();

            } else if ("ADJUSTMENT".equals(type)) {
                stock += transaction.getQuantity();
            }
        }

        return stock;
    }

    public int getLorryStock(Long productId, Long lorryId) {

        List<StockTransaction> transactions =
                stockTransactionRepository.findByProductIdAndLorryId(
                        productId,
                        lorryId
                );

        int stock = 0;

        for (StockTransaction transaction : transactions) {

            String type = transaction.getTransactionType();

            if ("LOAD_TO_LORRY".equals(type)) {
                stock += transaction.getQuantity();

            } else if ("SALE".equals(type)) {
                stock -= transaction.getQuantity();

            } else if ("RETURN".equals(type)) {
                stock -= transaction.getQuantity();

            } else if ("ADJUSTMENT".equals(type)) {
                stock += transaction.getQuantity();
            }
        }

        return stock;
    }
}