package com.example.javafxapp;
public class Orderin {
    private String id;
    private String accountid;
    private String name;

    private String price;

    private String description;

    private String orderdetailsid;

    private String supplierid;

    private String orderdate;

    public Orderin(String id, String accountid, String name, String price, String description, String orderdetailsid, String supplierid, String orderdate) {
        this.id = id;
        this.accountid = accountid;
        this.name = name;
        this.price = price;
        this.description = description;
        this.orderdetailsid = orderdetailsid;
        this.supplierid = supplierid;
        this.orderdate = orderdate;

    }

    public String getId() {
        return id;
    }
    public  String getAccountid(){return accountid;}

    public  String getOrderdetailsid(){return orderdetailsid;}

    public String getSupplierid(){return supplierid;}

    public String getPrice() {
        return price;
    }



    public String getName() {
        return name;
    }

    public String getOrderdate() {
        return orderdate;
    }

    public String getDescription() {
        return description;
    }
}

