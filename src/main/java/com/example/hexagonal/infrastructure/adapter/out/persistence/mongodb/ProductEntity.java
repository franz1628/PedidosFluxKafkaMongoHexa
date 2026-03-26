package com.example.hexagonal.infrastructure.adapter.out.persistence.mongodb;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
public class ProductEntity {
    @Id
    private String id;
    private String name;
    private Double price;

    public ProductEntity() {}

    public ProductEntity(String id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public static ProductEntityBuilder builder() {
        return new ProductEntityBuilder();
    }

    public static class ProductEntityBuilder {
        private String id;
        private String name;
        private Double price;

        public ProductEntityBuilder id(String id) { this.id = id; return this; }
        public ProductEntityBuilder name(String name) { this.name = name; return this; }
        public ProductEntityBuilder price(Double price) { this.price = price; return this; }
        public ProductEntity build() { return new ProductEntity(id, name, price); }
    }
}
