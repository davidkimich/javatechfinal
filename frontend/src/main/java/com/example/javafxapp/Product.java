package com.example.javafxapp;
public class Product {
    private String id;
    private String name;
    private String description;

    private String price;

    public Product(String id, String name, String description, String price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getPrice() {
        return price;
    }



    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String text) {
        this.name = text;
    }
    public void setPrice(String text) {
        this.price = text;
    }
    public void setDescription(String text) {
        this.description = text;
    }
}

