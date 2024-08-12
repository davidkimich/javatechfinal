package com.csis231.api.model;

import jakarta.persistence.*;

@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventoryid", nullable = false)
    private Long inventoryId;

    @Column(name = "inventorydescription")
    private String inventoryDescription;

    @Column(name = "itemQuantity")
    private Long itemQuantity;

    @Column(name = "productid")
    private Long productId;

    public Long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public String getInventoryDescription() {
        return inventoryDescription;
    }

    public void setInventoryDescription(String inventoryDescription) {
        this.inventoryDescription = inventoryDescription;
    }

    public Long getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(Long itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "inventoryid=" + inventoryId +
                ", inventorydescription='" + inventoryDescription + '\'' +
                ", itemQuantity=" + itemQuantity +
                ", productId=" + productId +
                '}';
    }
}
