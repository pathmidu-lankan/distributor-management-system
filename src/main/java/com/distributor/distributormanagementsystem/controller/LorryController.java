package com.distributor.distributormanagementsystem.controller;

import com.distributor.distributormanagementsystem.entity.Lorry;
import com.distributor.distributormanagementsystem.service.LorryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lorries")
public class LorryController {

    private final LorryService lorryService;

    public LorryController(LorryService lorryService) {
        this.lorryService = lorryService;
    }

    @PostMapping
    public Lorry addLorry(@RequestBody Lorry lorry) {
        return lorryService.addLorry(lorry);
    }

    @GetMapping
    public List<Lorry> getAllLorries() {
        return lorryService.getAllLorries();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lorry> getLorryById(@PathVariable Long id) {
        return lorryService.getLorryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Lorry updateLorry(
            @PathVariable Long id,
            @RequestBody Lorry lorry) {

        return lorryService.updateLorry(id, lorry);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLorry(@PathVariable Long id) {
        lorryService.deleteLorry(id);
        return ResponseEntity.noContent().build();
    }
}