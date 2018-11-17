package com.app.listing.ebay.rest;

import com.app.listing.ebay.services.InventoryService;
import com.mycompany.myapp.ebay.api.inventory.model.InventoryItemWithSkuLocaleGroupid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryRest {


    @Autowired
    InventoryService inventoryService;

    @GetMapping("/items")
    public List<InventoryItemWithSkuLocaleGroupid> getAllInventoryItem() {
        return inventoryService.getAllItems();
    }
}
