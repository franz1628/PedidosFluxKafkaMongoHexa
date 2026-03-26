package com.example.hexagonal.infrastructure.adapter.out.persistence.mongodb;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "orders")
public class OrderEntity {
    @Id
    private String id;
    private String customerId;
    private List<OrderItemEntity> items;
    private Double totalAmount;
    private String status;
    private LocalDateTime createdAt;

    public OrderEntity() {}

    public OrderEntity(String id, String customerId, List<OrderItemEntity> items, Double totalAmount, String status, LocalDateTime createdAt) {
        this.id = id;
        this.customerId = customerId;
        this.items = items;
        this.totalAmount = totalAmount;
        this.status = status;
        this.createdAt = createdAt;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    public List<OrderItemEntity> getItems() { return items; }
    public void setItems(List<OrderItemEntity> items) { this.items = items; }
    public Double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public static OrderEntityBuilder builder() {
        return new OrderEntityBuilder();
    }

    public static class OrderEntityBuilder {
        private String id;
        private String customerId;
        private List<OrderItemEntity> items;
        private Double totalAmount;
        private String status;
        private LocalDateTime createdAt;

        public OrderEntityBuilder id(String id) { this.id = id; return this; }
        public OrderEntityBuilder customerId(String customerId) { this.customerId = customerId; return this; }
        public OrderEntityBuilder items(List<OrderItemEntity> items) { this.items = items; return this; }
        public OrderEntityBuilder totalAmount(Double totalAmount) { this.totalAmount = totalAmount; return this; }
        public OrderEntityBuilder status(String status) { this.status = status; return this; }
        public OrderEntityBuilder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public OrderEntity build() { return new OrderEntity(id, customerId, items, totalAmount, status, createdAt); }
    }
}
