package com.distributor.distributormanagementsystem.service;

import com.distributor.distributormanagementsystem.entity.SaleItem;
import com.distributor.distributormanagementsystem.repository.SaleItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleItemService {

    private final SaleItemRepository saleItemRepository;

    public SaleItemService(SaleItemRepository saleItemRepository) {
        this.saleItemRepository = saleItemRepository;
    }

    public SaleItem addSaleItem(SaleItem saleItem) {
        return saleItemRepository.save(saleItem);
    }

    public List<SaleItem> getAllSaleItems() {
        return saleItemRepository.findAll();
    }

    public Optional<SaleItem> getSaleItemById(Long id) {
        return saleItemRepository.findById(id);
    }

    public List<SaleItem> getItemsBySaleId(Long saleId) {
        return saleItemRepository.findBySaleId(saleId);
    }

    public void deleteSaleItem(Long id) {
        saleItemRepository.deleteById(id);
    }
}