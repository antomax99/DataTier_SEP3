package com.example.datatier_sep3.models;

import com.example.datatier_sep3.contracts.OrderModel;
import com.example.datatier_sep3.daos.OrderDAOImpl;
import com.example.datatier_sep3.entities.Order;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class OrderModelImpl implements OrderModel {
    private OrderDAOImpl orderDAO;
    {
        try {
            orderDAO = OrderDAOImpl.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<Order> getAllOrders() throws IOException {
        return orderDAO.getAllOrders();
    }

    @Override
    public List<Order> getOrdersFromUser(int userID) throws IOException {
        return orderDAO.getOrdersFromUser(userID);
    }

    @Override
    public Order getOrderById(int id) {
        return orderDAO.getOrderById(id);
    }

    @Override
    public void addOrder(Order order) {
        orderDAO.addOrder(order);
    }

    @Override
    public void deleteOrderById(int id) {
        orderDAO.deleteOrderById(id);
    }

    @Override
    public void updateOrder(Order order) {
        orderDAO.updateOrder(order);
    }
}
