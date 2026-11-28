package com.distributor.distributormanagementsystem.service;

import com.distributor.distributormanagementsystem.entity.LorryLoading;
import com.distributor.distributormanagementsystem.entity.StockTransaction;
import com.distributor.distributormanagementsystem.repository.LorryLoadingRepository;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;

@Service
public class LorryLoadingService {

    private final LorryLoadingRepository lorryLoadingRepository;
    private final StockTransactionService stockTransactionService;

    public LorryLoadingService(
            LorryLoadingRepository lorryLoadingRepository,
            StockTransactionService stockTransactionService) {

        this.lorryLoadingRepository = lorryLoadingRepository;
        this.stockTransactionService = stockTransactionService;
    }

    public LorryLoading addLoading(LorryLoading loading) {

        int availableStock =
                stockTransactionService.getWarehouseStock(
                        loading.getProductId(),
                        loading.getWarehouseId()
                );

        if (loading.getQuantity() > availableStock) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Insufficient warehouse stock. Available stock: "
                            + availableStock
            );
        }

        LorryLoading savedLoading =
                lorryLoadingRepository.save(loading);

        StockTransaction transaction = new StockTransaction();

        transaction.setProductId(loading.getProductId());
        transaction.setWarehouseId(loading.getWarehouseId());
        transaction.setLorryId(loading.getLorryId());
        transaction.setTransactionType("LOAD_TO_LORRY");
        transaction.setQuantity(loading.getQuantity());
        transaction.setReferenceNumber(loading.getReferenceNumber());
        transaction.setRemarks("Lorry loading: " + loading.getRemarks());

        stockTransactionService.addTransaction(transaction);

        return savedLoading;
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