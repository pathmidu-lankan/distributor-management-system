package com.distributor.distributormanagementsystem.repository;

import com.distributor.distributormanagementsystem.entity.StockTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockTransactionRepository extends JpaRepository<StockTransaction, Long> {

    List<StockTransaction> findByProductIdAndWarehouseId(
            Long productId,
            Long warehouseId
    );

    List<StockTransaction> findByProductIdAndLorryId(
            Long productId,
            Long lorryId
    );
}