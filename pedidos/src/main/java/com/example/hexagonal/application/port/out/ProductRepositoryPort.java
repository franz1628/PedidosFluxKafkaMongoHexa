package com.example.hexagonal.application.port.out;

import com.example.hexagonal.domain.model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepositoryPort {
    Product save(Product product);
    Optional<Product> findById(String id);
    List<Product> findAll();
    void deleteById(String id);
}
