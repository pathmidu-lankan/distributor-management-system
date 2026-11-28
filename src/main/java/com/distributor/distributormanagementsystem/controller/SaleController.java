package com.distributor.distributormanagementsystem.controller;

import com.distributor.distributormanagementsystem.entity.Sale;
import com.distributor.distributormanagementsystem.entity.SaleItem;
import com.distributor.distributormanagementsystem.service.SaleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping
    public Sale addSale(
            @Valid @RequestBody SaleRequest request) {
        return saleService.addSale(
                request.getSale(),
                request.getItems()
        );
    }

    @GetMapping
    public List<Sale> getAllSales() {
        return saleService.getAllSales();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sale> getSaleById(
            @PathVariable Long id) {

        return saleService.getSaleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale(
            @PathVariable Long id) {

        saleService.deleteSale(id);
        return ResponseEntity.noContent().build();
    }

    public static class SaleRequest {

        private Sale sale;
        @Valid
        private List<SaleItem> items;
        public Sale getSale() {
            return sale;
        }

        public void setSale(Sale sale) {
            this.sale = sale;
        }

        public List<SaleItem> getItems() {
            return items;
        }

        public void setItems(List<SaleItem> items) {
            this.items = items;
        }
    }
}