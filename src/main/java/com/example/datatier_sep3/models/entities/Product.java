package com.example.datatier_sep3.models.entities;

import java.io.Serializable;

public class Product implements Serializable {

    private int productId;
    private String name;
    private String brand;
    private String description;


    public Product() {
    }

    public Product(String name, String brand, String description) {
        this.name = name;
        this.brand = brand;
        this.description = description;
    }

    public Product(int productId, String name, String brand, String description) {
        this.productId = productId;
        this.name = name;
        this.brand = brand;
        this.description = description;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
