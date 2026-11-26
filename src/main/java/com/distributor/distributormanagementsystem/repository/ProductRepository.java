package com.distributor.distributormanagementsystem.repository;

import com.distributor.distributormanagementsystem.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}