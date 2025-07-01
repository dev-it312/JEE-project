package com.kamel.inventoryservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @GetMapping("/sample")
    public String getSampleInventory() {
        return "This is a sample inventory item from Inventory Service!";
    }
}
