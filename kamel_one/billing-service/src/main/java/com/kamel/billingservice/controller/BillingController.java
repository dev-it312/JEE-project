package com.kamel.billingservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/billing")
public class BillingController {

    @GetMapping("/sample")
    public String getSampleBilling() {
        return "This is a sample bill from Billing Service!";
    }
}
