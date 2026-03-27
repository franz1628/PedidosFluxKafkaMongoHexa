package com.example.hexagonal.application.port.in;

import com.example.hexagonal.domain.model.Product;

public interface CreateProductUseCase {
    Product createProduct(Product product);
}
