package com.example.datatier_sep3.contracts;

import com.example.datatier_sep3.entities.Order;

import java.io.IOException;
import java.util.List;

public interface OrderModel {
    List<Order> getAllOrders() throws IOException;
    public List<Order> getOrdersFromUser(int userID) throws IOException;
    Order getOrderById(int id);
    void addOrder(Order user) ;
    void deleteOrderById(int id);
    void updateOrder(Order user);
}
