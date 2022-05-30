package com.example.datatier_sep3.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable {
    private int orderId;
    private int customerId;
    private double price;
    protected List<Product> products;
    private boolean isCompleted;


    /**
     * Empty constructor for Serializable
     */
    public Order() {}


    /** Main constructor
     * @param orderId the ID for database, should not be changed
     * @param customerId the ID of the order 'owner' for database, used as a foreign key for linking orders to users in database
     * @param price the price of the Order
     * @param isCompleted denotes if the order is considered a wishlist or completed
     * @
     */
    public Order(int orderId, int customerId, double price, boolean isCompleted) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.price = price;
        this.products = new ArrayList<>();
        this.isCompleted = isCompleted;
    }

    /**
     * Partial constructor with list of products
     * @param orderId the ID for database, should not be changed
     * @param price the price of the Order
     * @param products list of products in the order
     * @param isCompleted denotes if the order is considered a wishlist or completed
     */
    public Order(int orderId, double price, List<Product> products, boolean isCompleted) {
        this.orderId = orderId;
        this.price = price;
        this.products = products;
        this.isCompleted = isCompleted;
    }


    /**
     * Full constructor
     * @param orderId the ID for database, should not be changed
     * @param customerId the ID of the order 'owner' for database, used as a foreign key for linking orders to users in database
     * @param price the price of the Order
     * @param products list of products in the order
     * @param isCompleted denotes if the order is considered a wishlist or completed
     */
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
        int newValue=0;
        for(Product p : this.products){
            newValue+=p.getValue();
        }
        this.price=newValue;
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
