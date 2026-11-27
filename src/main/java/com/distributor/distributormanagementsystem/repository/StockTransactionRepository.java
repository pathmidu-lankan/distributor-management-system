package com.distributor.distributormanagementsystem.repository;

import com.distributor.distributormanagementsystem.entity.StockTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockTransactionRepository extends JpaRepository<StockTransaction, Long> {

}