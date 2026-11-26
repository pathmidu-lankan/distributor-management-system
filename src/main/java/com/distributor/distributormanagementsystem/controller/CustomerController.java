package com.distributor.distributormanagementsystem.controller;

import com.distributor.distributormanagementsystem.entity.Customer;
import com.distributor.distributormanagementsystem.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // Add a new customer
    @PostMapping
    public Customer addCustomer(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }

    // Get all customers
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    // Get customer by ID
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {

        return customerService.getCustomerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update customer
    @PutMapping("/{id}")
    public Customer updateCustomer(
            @PathVariable Long id,
            @RequestBody Customer customer) {

        return customerService.updateCustomer(id, customer);
    }

    // Delete customer
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {

        customerService.deleteCustomer(id);

        return ResponseEntity.noContent().build();
    }
}