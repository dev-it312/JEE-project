package com.kamel.customerservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @GetMapping("/sample")
    public String getSampleCustomer() {
        return "This is a sample customer from Customer Service!";
    }
}
