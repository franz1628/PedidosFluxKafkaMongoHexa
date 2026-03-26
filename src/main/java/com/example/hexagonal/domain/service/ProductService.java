package com.example.hexagonal.domain.service;

import com.example.hexagonal.application.port.in.CreateProductUseCase;
import com.example.hexagonal.application.port.out.ProductRepositoryPort;
import com.example.hexagonal.domain.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements CreateProductUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    public ProductService(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    @Override
    public Product createProduct(Product product) {
        return productRepositoryPort.save(product);
    }

    public Optional<Product> getProductById(String id) {
        return productRepositoryPort.findById(id);
    }

    public List<Product> getAllProducts() {
        return productRepositoryPort.findAll();
    }
}
