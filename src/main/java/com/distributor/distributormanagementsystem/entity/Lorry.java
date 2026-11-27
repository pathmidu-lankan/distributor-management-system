package com.distributor.distributormanagementsystem.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "lorries")
public class Lorry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String lorryCode;

    @Column(nullable = false, unique = true)
    private String registrationNumber;

    private String description;

    private boolean active = true;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLorryCode() {
        return lorryCode;
    }

    public void setLorryCode(String lorryCode) {
        this.lorryCode = lorryCode;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}