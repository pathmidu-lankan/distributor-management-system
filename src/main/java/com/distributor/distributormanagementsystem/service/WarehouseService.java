package com.distributor.distributormanagementsystem.service;

import com.distributor.distributormanagementsystem.entity.Warehouse;
import com.distributor.distributormanagementsystem.repository.WarehouseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;

    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    // Add a new warehouse
    public Warehouse addWarehouse(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    // Get all warehouses
    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    // Get warehouse by ID
    public Optional<Warehouse> getWarehouseById(Long id) {
        return warehouseRepository.findById(id);
    }

    // Update warehouse
    public Warehouse updateWarehouse(Long id, Warehouse updatedWarehouse) {

        Warehouse existingWarehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));

        existingWarehouse.setWarehouseCode(updatedWarehouse.getWarehouseCode());
        existingWarehouse.setWarehouseName(updatedWarehouse.getWarehouseName());
        existingWarehouse.setActive(updatedWarehouse.isActive());

        return warehouseRepository.save(existingWarehouse);
    }

    // Delete warehouse
    public void deleteWarehouse(Long id) {
        warehouseRepository.deleteById(id);
    }
}