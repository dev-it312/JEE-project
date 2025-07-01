package com.kamel.productservice.controller;

import com.kamel.productservice.model.Product;
import com.kamel.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.util.List;

@RestController
@RequestMapping("/products")
@RefreshScope // Enable refreshing properties from Config Server
public class ProductController {

    private final ProductRepository productRepository;

    @Value("${product.service.custom.message:Default message if not found in config}")
    private String customMessage;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/sample")
    public String getSampleProduct() {
        return "Configured message: " + customMessage + " ---- Try /products/all or POST to /products";
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = productRepository.save(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            // Optionally, add a default product if none exist for demonstration
            Product defaultProduct = new Product("Default Laptop", "High-performance laptop", 1200.99);
            productRepository.save(defaultProduct);
            products.add(defaultProduct);
        }
        return ResponseEntity.ok(products);
    }
}
