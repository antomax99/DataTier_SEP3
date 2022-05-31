package com.example.datatier_sep3.daos;

import com.example.datatier_sep3.entities.Order;
import com.example.datatier_sep3.entities.Product;


import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//TODO: Test all methods
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
                OrdersFound.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(OrdersFound.toString());
        return OrdersFound;
    }

    @Override
    public List<Order> getOrdersFromUser(int userID) throws IOException {

        List<Order> OrdersFound = new ArrayList<>();
        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM public.order o INNER JOIN public.users u on o.\"customerId\"=u.id where u.id="+userID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int orderId = resultSet.getInt("id");
                int customerId = resultSet.getInt("customerId");
                double price = resultSet.getDouble("price");
                boolean completed = resultSet.getBoolean("completed");
                Order order = new Order(orderId,customerId,price,null,completed);
                OrdersFound.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return OrdersFound;

    }

    @Override
    public Order getOrderById(int id) {
        Order orderFound = null;
        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM database_sep3.public.order, database_sep3.public.order_products, database_sep3.public.products WHERE database_sep3.public.products.id = database_sep3.public.order_products.product_id AND database_sep3.public.order.id = database_sep3.public.order_products.order_id ");
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.isBeforeFirst()){
                if (resultSet.next()) {
                   int productId = resultSet.getInt("id");
                   String name = resultSet.getString("name");
                   String brand = resultSet.getString("brand");
                   String description = resultSet.getString("description");
                   double value = resultSet.getDouble("value");
                   Product productFound = new Product(productId,name,brand,description,value);
                   List<Product> products = new ArrayList<>();
                   products.add(productFound);

                   int orderId = resultSet.getInt("id");
                   int customerId = resultSet.getInt("customerId");
                   double price = resultSet.getDouble("price");
                   boolean completed = resultSet.getBoolean("completed");
                   orderFound = new Order(orderId,customerId,price,products,completed);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderFound;
    }

    @Override
    public void addOrder(Order order) {
        List<Product> products = order.getProducts();
        try (Connection connection = getConnection()) {

            for(int i = 0; i < products.size(); i ++)
            {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO database_sep3.public.order_products (order_id,product_id) VALUES (?,?)");
                statement.setInt(1,order.getOrderId());
                statement.setInt(2,products.get(i).getProductId());
            }

            //TODO: Add products to order
            PreparedStatement statement = connection.prepareStatement("INSERT INTO database_sep3.public.order (orderId,customerId,price, isCompleted) VALUES (?,?,?,?)",  PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1, order.getOrderId());
            statement.setInt(2, order.getCustomerId());
            statement.setDouble(3, order.getPrice());
            statement.setBoolean(4, order.isCompleted());
            statement.executeUpdate();

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
