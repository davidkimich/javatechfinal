package com.csis231.api.model;

import jakarta.persistence.*;

@Entity
public class supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplierid", nullable = false)
    private Long supplierId;

    @Column(name = "suppliername", nullable = false)
    private String supplierName;

    @Column(name = "supplierdescription")
    private String supplierDescription;

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierDescription() {
        return supplierDescription;
    }

    public void setSupplierDescription(String supplierDescription) {
        this.supplierDescription = supplierDescription;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "supplierId=" + supplierId +
                ", supplierName='" + supplierName + '\'' +
                ", supplierDescription='" + supplierDescription + '\'' +
                '}';
    }
}
