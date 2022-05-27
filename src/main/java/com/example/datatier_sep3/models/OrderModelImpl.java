package com.example.datatier_sep3.models;

import com.example.datatier_sep3.contracts.OrderModel;
import com.example.datatier_sep3.daos.OrderDAOImpl;
import com.example.datatier_sep3.daos.UserDAOImpl;
import com.example.datatier_sep3.models.entities.Order;

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
    public Order getOrderById(int id) {
        return null;
    }

    @Override
    public void addOrder(Order user) {

    }

    @Override
    public void deleteOrderById(int id) {

    }

    @Override
    public void updateOrder(Order user) {

    }
}
