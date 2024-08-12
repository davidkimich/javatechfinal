package com.example.javafxapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;

public class Orderdetails {
    private String orderid;
    private String productid;
    private String quantity;
    private String price;
    private String description;

    public Orderdetails(String orderid, String productid, String quantity, String price, String description) {
        this.orderid = orderid;
        this.productid = productid;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
    }


    @Override
    public String toString() {
        return "Orderdetails{" +
                "id='" + orderid + '\'' +
                ", productid='" + productid + '\'' +
                ", quantity='" + quantity + '\'' +
                ", price='" + price + '\'' +
                ", description='" + description + '\'' +
                '}';
    }



    public String getOrderid() {
        return orderid;
    }

    public String getProductid() {
        return productid;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
    // Sample Database class
    static class Database {
        public Connection getConnection() throws SQLException {
            // Implement your database connection logic here
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/database_name", "username", "password");
        }
    }
}
