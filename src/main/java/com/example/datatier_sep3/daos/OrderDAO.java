package com.example.datatier_sep3.daos;

import com.example.datatier_sep3.models.entities.Order;

import java.io.IOException;
import java.util.List;

public interface OrderDAO {
    List<Order> getAllOrders() throws IOException;
    Order getOrderById(int id);
    void addOrder(Order user) ;
    void deleteOrderById(int id);
    void updateOrder(Order user);

}
