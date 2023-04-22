package com.fashion.service;

import com.fashion.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class InventoryService {

    private InventoryRepository inventoryRepository;

    public boolean checkProductInStockInventory(String skuCode) {
        return inventoryRepository.findBySkuCode(skuCode).isPresent();
    }
}
