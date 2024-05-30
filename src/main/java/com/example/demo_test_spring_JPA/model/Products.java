package com.example.demo_test_spring_JPA.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Products {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "product_name")
    private String productName;

    public Integer getId() {
        return this.id;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
