package com.distributor.distributormanagementsystem.service;

import com.distributor.distributormanagementsystem.entity.Sale;
import com.distributor.distributormanagementsystem.entity.SaleItem;
import com.distributor.distributormanagementsystem.entity.StockTransaction;
import com.distributor.distributormanagementsystem.repository.SaleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    private final SaleRepository saleRepository;
    private final SaleItemService saleItemService;
    private final StockTransactionService stockTransactionService;

    public SaleService(
            SaleRepository saleRepository,
            SaleItemService saleItemService,
            StockTransactionService stockTransactionService) {

        this.saleRepository = saleRepository;
        this.saleItemService = saleItemService;
        this.stockTransactionService = stockTransactionService;
    }

    @Transactional
    public Sale addSale(Sale sale, List<SaleItem> items) {

        if (items == null || items.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Sale must contain at least one item"
            );
        }

        // Check every sale item before saving anything
        for (SaleItem item : items) {

            // Validate quantity
            if (item.getQuantity() == null || item.getQuantity() < 1) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Quantity must be at least 1"
                );
            }

            // Check lorry stock
            int availableStock =
                    stockTransactionService.getLorryStock(
                            item.getProductId(),
                            sale.getLorryId()
                    );

            if (item.getQuantity() > availableStock) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Insufficient lorry stock for product ID "
                                + item.getProductId()
                                + ". Available stock: "
                                + availableStock
                );
            }
        }

        // Save sale
        Sale savedSale = saleRepository.save(sale);

        // Save sale items and create stock transactions
        for (SaleItem item : items) {

            item.setSaleId(savedSale.getId());

            saleItemService.addSaleItem(item);

            StockTransaction transaction = new StockTransaction();

            transaction.setProductId(item.getProductId());
            transaction.setLorryId(sale.getLorryId());
            transaction.setTransactionType("SALE");
            transaction.setQuantity(item.getQuantity());

            transaction.setReferenceNumber(
                    savedSale.getInvoiceNumber()
            );

            transaction.setRemarks(
                    "Sale: " + savedSale.getInvoiceNumber()
            );

            stockTransactionService.addTransaction(transaction);
        }

        return savedSale;
    }

    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    public Optional<Sale> getSaleById(Long id) {
        return saleRepository.findById(id);
    }

    public void deleteSale(Long id) {
        saleRepository.deleteById(id);
    }
}