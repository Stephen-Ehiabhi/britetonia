package com.britetonia.controller;

import com.britetonia.dto.InventoryResponse;
import com.britetonia.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;


    @GetMapping("")
    @Transactional(readOnly = true)
    public ResponseEntity<List<InventoryResponse>> IsInStockController (@RequestParam List<String> productNames){
        return new ResponseEntity<>(inventoryService.checkProductInStockInventory(productNames), HttpStatus.OK);
    }

}
