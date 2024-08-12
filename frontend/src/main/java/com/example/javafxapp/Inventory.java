package com.example.javafxapp;
public class Inventory {
    private String id;
    private String description;
    private String quantity;

    private String productid;

    private String productname;

    public Inventory(String id, String description, String quantity, String productid) {
        this.id = id;
        this.description = description;
        this.quantity = quantity;
        this.productid = productid;

    }

    public String getId() {
        return id;
    }



    public String getProductid() {
        return productid;
    }



    public String getQuantity() {
        return quantity;
    }

    public String getDescription() {
        return description;
    }
}

