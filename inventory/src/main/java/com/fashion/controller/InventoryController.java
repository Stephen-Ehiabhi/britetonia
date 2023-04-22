package com.fashion.controller;

import com.fashion.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;


    @GetMapping("{sku-code}")
    @Transactional(readOnly = true)
    public ResponseEntity<Boolean> IsInStockController (@PathVariable("sku-code") String skuCode){
        return new ResponseEntity<>(inventoryService.checkProductInStockInventory(skuCode), HttpStatus.OK);
    }

}
