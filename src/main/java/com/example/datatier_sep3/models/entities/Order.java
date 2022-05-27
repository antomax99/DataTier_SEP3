package com.example.datatier_sep3.models.entities;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {
    private int orderId;
    private int customerId;
    private double price;
    protected List<Product> products;
    private boolean isCompleted;

    public Order() {}

    public Order(int orderId, double price, List<Product> products, boolean isCompleted) {
        this.orderId = orderId;
        this.price = price;
        this.products = products;
        this.isCompleted = isCompleted;
    }

    public Order(int orderId, int customerId, double price, List<Product> products, boolean isCompleted) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.price = price;
        this.products = products;
        this.isCompleted = isCompleted;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", customerId=" + customerId +
                ", price=" + price +
                ", products=" + products +
                ", isCompleted=" + isCompleted +
                '}';
    }
}
