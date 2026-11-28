package com.distributor.distributormanagementsystem.repository;

import com.distributor.distributormanagementsystem.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}