package com.distributor.distributormanagementsystem.repository;

import com.distributor.distributormanagementsystem.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}