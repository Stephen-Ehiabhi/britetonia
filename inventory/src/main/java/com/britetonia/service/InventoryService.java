package com.britetonia.service;

import com.britetonia.dto.InventoryResponse;
import com.britetonia.model.Inventory;
import com.britetonia.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public List<InventoryResponse> checkProductInStockInventory(List<String> skuCode) {
        return inventoryRepository.findByProductNameIn(skuCode)
                .stream()
                .map(this::mapRequestToDTO).toList();
    }

    private InventoryResponse mapRequestToDTO(Inventory inventory) {
        return InventoryResponse.builder()
                .productName(inventory.getProductName())
                .IsInStock(inventory.getQuantity() > 0)
                .build();
    }
}
