package com.distributor.distributormanagementsystem.service;

import com.distributor.distributormanagementsystem.entity.Customer;
import com.distributor.distributormanagementsystem.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Add a new customer
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // Get all customers
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Get customer by ID
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    // Update customer
    public Customer updateCustomer(Long id, Customer updatedCustomer) {

        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        existingCustomer.setCustomerCode(updatedCustomer.getCustomerCode());
        existingCustomer.setShopName(updatedCustomer.getShopName());
        existingCustomer.setOwnerName(updatedCustomer.getOwnerName());
        existingCustomer.setPhone(updatedCustomer.getPhone());
        existingCustomer.setAddress(updatedCustomer.getAddress());
        existingCustomer.setArea(updatedCustomer.getArea());
        existingCustomer.setCreditLimit(updatedCustomer.getCreditLimit());
        existingCustomer.setPaymentTermsDays(updatedCustomer.getPaymentTermsDays());
        existingCustomer.setActive(updatedCustomer.isActive());

        return customerRepository.save(existingCustomer);
    }

    // Delete customer
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}