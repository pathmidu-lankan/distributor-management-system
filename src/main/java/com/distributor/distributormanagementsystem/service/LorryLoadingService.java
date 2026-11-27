package com.distributor.distributormanagementsystem.service;

import com.distributor.distributormanagementsystem.entity.LorryLoading;
import com.distributor.distributormanagementsystem.repository.LorryLoadingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LorryLoadingService {

    private final LorryLoadingRepository lorryLoadingRepository;

    public LorryLoadingService(LorryLoadingRepository lorryLoadingRepository) {
        this.lorryLoadingRepository = lorryLoadingRepository;
    }

    public LorryLoading addLoading(LorryLoading loading) {
        return lorryLoadingRepository.save(loading);
    }

    public List<LorryLoading> getAllLoadings() {
        return lorryLoadingRepository.findAll();
    }

    public Optional<LorryLoading> getLoadingById(Long id) {
        return lorryLoadingRepository.findById(id);
    }

    public void deleteLoading(Long id) {
        lorryLoadingRepository.deleteById(id);
    }
}