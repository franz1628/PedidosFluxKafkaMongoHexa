package com.example.inventarios.domain.model;

public class InventoryItem {
    private String id;
    private String productId;
    private Integer quantity;

    public InventoryItem() {}

    public InventoryItem(String id, String productId, Integer quantity) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
}
