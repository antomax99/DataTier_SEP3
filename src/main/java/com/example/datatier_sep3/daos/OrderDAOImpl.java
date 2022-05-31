package com.example.datatier_sep3.daos;

import com.example.datatier_sep3.daos.interfaces.OrderDAO;
import com.example.datatier_sep3.entities.Order;
import com.example.datatier_sep3.entities.Product;


import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
//TODO: Test all methods
public class OrderDAOImpl implements OrderDAO {

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
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/database_sep3", "postgres", "123456");
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
                OrdersFound.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(OrdersFound.toString());
        return OrdersFound;
    }

    //TODO: Add products to order
    @Override
    public List<Order> getOrdersFromUser(int userID) throws IOException {

        List<Order> OrdersFound = new ArrayList<>();
        try(Connection connection = getConnection()) {

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM public.order o INNER JOIN public.users u on o.\"customerId\"=u.id where u.id="+userID);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){

                int id = resultSet.getInt("id");
                Double price = resultSet.getDouble("price");
                boolean isCompleted = resultSet.getBoolean("completed");

                Product p = new Product("name","test product brand","test product description", 245);
                ArrayList<Product>products = new ArrayList<Product>();
                products.add(p);products.add(p);products.add(p);products.add(p);

                Order order = new Order(id,userID,price,products,isCompleted);
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

        Order orderFound = null;
        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM database_sep3.public.order WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.isBeforeFirst()){
                if (resultSet.next()) {
                    int customerId = resultSet.getInt("customerId");
                    Double price = resultSet.getDouble("price");
                    boolean isCompleted = resultSet.getBoolean("completed");
                    //TODO: Get all attached products
                    Product p = new Product("name","test product brand","test product description", 245);
                    ArrayList<Product>products = new ArrayList<Product>();
                    products.add(p);products.add(p);products.add(p);products.add(p);
                    orderFound = new Order(id,customerId,price,products,isCompleted);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderFound;
    }

    @Override
    public void addOrder(Order order) {
        Order result = null;
        try (Connection connection = getConnection()) {
            //TODO: Add products to order
            PreparedStatement statement = connection.prepareStatement("INSERT INTO database_sep3.public.order (\"customerId\",price,completed ) VALUES (?,?, ?)",  PreparedStatement.RETURN_GENERATED_KEYS);

            statement.setInt(1, order.getCustomerId());
            statement.setDouble(2, order.getPrice());
            statement.setBoolean(3, order.isCompleted());
            //statement.setString(5, order.getProducts());
            statement.executeUpdate();

            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                result = new Order (keys.getInt(1), order.getCustomerId() , order.getPrice(), order.isCompleted());
            } else {
                throw new SQLException("No key generated");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrderById(int id) {
        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM database_sep3.public.order WHERE id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void updateOrder(Order order) {

        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE database_sep3.public.users SET customerId = ?, price = ?, isCompleted = ? WHERE id = ?");
            statement.setInt(1, order.getCustomerId());
            statement.setDouble(2, order.getPrice());
            statement.setBoolean(3, order.isCompleted());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
