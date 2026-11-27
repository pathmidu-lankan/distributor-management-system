package com.distributor.distributormanagementsystem.service;

import com.distributor.distributormanagementsystem.entity.Lorry;
import com.distributor.distributormanagementsystem.repository.LorryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LorryService {

    private final LorryRepository lorryRepository;

    public LorryService(LorryRepository lorryRepository) {
        this.lorryRepository = lorryRepository;
    }

    public Lorry addLorry(Lorry lorry) {
        return lorryRepository.save(lorry);
    }

    public List<Lorry> getAllLorries() {
        return lorryRepository.findAll();
    }

    public Optional<Lorry> getLorryById(Long id) {
        return lorryRepository.findById(id);
    }

    public Lorry updateLorry(Long id, Lorry updatedLorry) {
        Lorry existingLorry = lorryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lorry not found"));

        existingLorry.setLorryCode(updatedLorry.getLorryCode());
        existingLorry.setRegistrationNumber(updatedLorry.getRegistrationNumber());
        existingLorry.setDescription(updatedLorry.getDescription());
        existingLorry.setActive(updatedLorry.isActive());

        return lorryRepository.save(existingLorry);
    }

    public void deleteLorry(Long id) {
        lorryRepository.deleteById(id);
    }
}