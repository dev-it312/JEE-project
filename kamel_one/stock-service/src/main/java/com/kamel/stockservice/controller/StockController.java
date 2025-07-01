package com.kamel.stockservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
public class StockController {

    @GetMapping("/sample")
    public String getSampleStock() {
        return "This is a sample stock item from Stock Service!";
    }
}
