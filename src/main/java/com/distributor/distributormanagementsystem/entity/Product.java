package com.distributor.distributormanagementsystem.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String productCode;

    @Column(nullable = false)
    private String productName;

    private String category;

    private String unit;

    private BigDecimal companyPrice;

    private BigDecimal distributorPrice;

    private BigDecimal sellingPrice;

    private BigDecimal discount;

    private boolean active = true;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getCompanyPrice() {
        return companyPrice;
    }

    public void setCompanyPrice(BigDecimal companyPrice) {
        this.companyPrice = companyPrice;
    }

    public BigDecimal  getDistributorPrice() {
        return distributorPrice;
    }

    public void setDistributorPrice(BigDecimal  distributorPrice) {
        this.distributorPrice = distributorPrice;
    }

    public BigDecimal  getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal  sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public BigDecimal  getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal  discount) {
        this.discount = discount;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}