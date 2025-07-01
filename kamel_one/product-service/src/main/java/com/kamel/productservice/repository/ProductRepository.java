package com.kamel.productservice.repository;

import com.kamel.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // JpaRepository provides CRUD operations (save, findById, findAll, delete, etc.)
    // We can add custom query methods here if needed later.
}
