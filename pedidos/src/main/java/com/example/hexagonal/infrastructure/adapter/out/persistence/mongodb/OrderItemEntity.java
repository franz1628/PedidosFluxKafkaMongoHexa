package com.example.hexagonal.infrastructure.adapter.out.persistence.mongodb;

public class OrderItemEntity {
    private String productId;
    private Integer quantity;
    private Double price;

    public OrderItemEntity() {}

    public OrderItemEntity(String productId, Integer quantity, Double price) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public static OrderItemEntityBuilder builder() {
        return new OrderItemEntityBuilder();
    }

    public static class OrderItemEntityBuilder {
        private String productId;
        private Integer quantity;
        private Double price;

        public OrderItemEntityBuilder productId(String productId) { this.productId = productId; return this; }
        public OrderItemEntityBuilder quantity(Integer quantity) { this.quantity = quantity; return this; }
        public OrderItemEntityBuilder price(Double price) { this.price = price; return this; }
        public OrderItemEntity build() { return new OrderItemEntity(productId, quantity, price); }
    }
}
