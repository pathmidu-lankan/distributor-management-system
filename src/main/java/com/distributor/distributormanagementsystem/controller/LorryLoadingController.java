package com.distributor.distributormanagementsystem.controller;

import com.distributor.distributormanagementsystem.entity.LorryLoading;
import com.distributor.distributormanagementsystem.service.LorryLoadingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lorry-loadings")
public class LorryLoadingController {

    private final LorryLoadingService lorryLoadingService;

    public LorryLoadingController(LorryLoadingService lorryLoadingService) {
        this.lorryLoadingService = lorryLoadingService;
    }

    @PostMapping
    public LorryLoading addLoading(
            @RequestBody LorryLoading loading) {
        return lorryLoadingService.addLoading(loading);
    }

    @GetMapping
    public List<LorryLoading> getAllLoadings() {
        return lorryLoadingService.getAllLoadings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LorryLoading> getLoadingById(
            @PathVariable Long id) {
        return lorryLoadingService.getLoadingById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoading(
            @PathVariable Long id) {
        lorryLoadingService.deleteLoading(id);
        return ResponseEntity.noContent().build();
    }
}