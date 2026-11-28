package com.distributor.distributormanagementsystem.controller;

import com.distributor.distributormanagementsystem.entity.SaleItem;
import com.distributor.distributormanagementsystem.service.SaleItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sale-items")
public class SaleItemController {

    private final SaleItemService saleItemService;

    public SaleItemController(SaleItemService saleItemService) {
        this.saleItemService = saleItemService;
    }

    @PostMapping
    public SaleItem addSaleItem(@RequestBody SaleItem saleItem) {
        return saleItemService.addSaleItem(saleItem);
    }

    @GetMapping
    public List<SaleItem> getAllSaleItems() {
        return saleItemService.getAllSaleItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleItem> getSaleItemById(
            @PathVariable Long id) {

        return saleItemService.getSaleItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/sale/{saleId}")
    public List<SaleItem> getItemsBySaleId(
            @PathVariable Long saleId) {

        return saleItemService.getItemsBySaleId(saleId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSaleItem(
            @PathVariable Long id) {

        saleItemService.deleteSaleItem(id);
        return ResponseEntity.noContent().build();
    }
}