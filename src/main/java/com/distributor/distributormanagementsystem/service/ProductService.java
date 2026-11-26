package com.distributor.distributormanagementsystem.service;

import com.distributor.distributormanagementsystem.entity.Product;
import com.distributor.distributormanagementsystem.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Add a new product
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    // Get all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Get product by ID
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    // Update product
    public Product updateProduct(Long id, Product updatedProduct) {

        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        existingProduct.setProductCode(updatedProduct.getProductCode());
        existingProduct.setProductName(updatedProduct.getProductName());
        existingProduct.setCategory(updatedProduct.getCategory());
        existingProduct.setUnit(updatedProduct.getUnit());
        existingProduct.setCompanyPrice(updatedProduct.getCompanyPrice());
        existingProduct.setDistributorPrice(updatedProduct.getDistributorPrice());
        existingProduct.setSellingPrice(updatedProduct.getSellingPrice());
        existingProduct.setDiscount(updatedProduct.getDiscount());
        existingProduct.setActive(updatedProduct.isActive());

        return productRepository.save(existingProduct);
    }

    // Delete product
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}