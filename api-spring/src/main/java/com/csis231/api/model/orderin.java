package com.csis231.api.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class orderin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderid", nullable = false)
    private Long orderId;

    @Column(name = "accountid", nullable = false)
    private Long accountId;

    @Column(name = "ordername")
    private String orderName;

    @Column(name = "orderprice")
    private double orderPrice;


    @Column(name = "orderdescription")
    private String orderDescription;

    @Column(name = "orderdetailsid")
    private Long orderDetailsId;

    @Column(name = "supplierid")
    private Long supplierId;


    @Column(name = "orderdate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date orderDate;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }

    public Long getOrderDetailsId() {
        return orderDetailsId;
    }

    public void setOrderDetailsId(Long orderDetailsId) {
        this.orderDetailsId = orderDetailsId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", accountId=" + accountId +
                ", orderName='" + orderName + '\'' +
                ", orderPrice=" + orderPrice +
                ", supplierId='" + supplierId + '\'' +
                ", orderDescription='" + orderDescription + '\'' +
                ", orderDetailsId=" + orderDetailsId +
                ", orderDate=" + orderDate +
                '}';
    }
}
