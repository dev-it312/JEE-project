package com.kamel.supplierservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

    @GetMapping("/sample")
    public String getSampleSupplier() {
        return "This is a sample supplier from Supplier Service!";
    }
}
