package com.example.datatier_sep3.daos;

import com.example.datatier_sep3.models.entities.Order;
import com.example.datatier_sep3.models.entities.User;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO{

    private static OrderDAOImpl instance;

    private OrderDAOImpl() throws SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
    }

    public static synchronized OrderDAOImpl getInstance() throws SQLException {
        if (instance == null) {
            instance = new OrderDAOImpl();
        }
        return instance;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/database_sep3", "postgres", "1234");
    }

    @Override
    public List<Order> getAllOrders() throws IOException {
        List<Order> OrdersFound = new ArrayList<>();
        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM database_sep3.public.order WHERE price >=0");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                System.out.println(id);
                int customerId = resultSet.getInt("customerId");
                Double price = resultSet.getDouble("price");
                boolean isCompleted = resultSet.getBoolean("completed");
                Order order = new Order(id,price,null,isCompleted);
                System.out.println(order.toString());
                OrdersFound.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(OrdersFound.toString());
        return OrdersFound;
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
