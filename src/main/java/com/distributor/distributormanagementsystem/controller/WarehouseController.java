package com.distributor.distributormanagementsystem.controller;

import com.distributor.distributormanagementsystem.entity.Warehouse;
import com.distributor.distributormanagementsystem.service.WarehouseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    // Add a new warehouse
    @PostMapping
    public Warehouse addWarehouse(@RequestBody Warehouse warehouse) {
        return warehouseService.addWarehouse(warehouse);
    }

    // Get all warehouses
    @GetMapping
    public List<Warehouse> getAllWarehouses() {
        return warehouseService.getAllWarehouses();
    }

    // Get warehouse by ID
    @GetMapping("/{id}")
    public ResponseEntity<Warehouse> getWarehouseById(@PathVariable Long id) {

        return warehouseService.getWarehouseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update warehouse
    @PutMapping("/{id}")
    public Warehouse updateWarehouse(
            @PathVariable Long id,
            @RequestBody Warehouse warehouse) {

        return warehouseService.updateWarehouse(id, warehouse);
    }

    // Delete warehouse
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWarehouse(@PathVariable Long id) {

        warehouseService.deleteWarehouse(id);

        return ResponseEntity.noContent().build();
    }
}