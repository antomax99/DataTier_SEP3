package com.example.datatier_sep3.daos;

import com.example.datatier_sep3.models.entities.Product;
import com.example.datatier_sep3.models.entities.User;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO{

    private static ProductDAOImpl instance;

    private ProductDAOImpl() throws SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
    }

    public static synchronized ProductDAOImpl getInstance() throws SQLException {
        if (instance == null) {
            instance = new ProductDAOImpl();
        }
        return instance;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/database_sep3", "postgres", "123456");
    }

    @Override
    public List<Product> getAllProducts() throws IOException {
        List<Product> productFound = new ArrayList<>();
        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM database_sep3.public.products WHERE value >= 0");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String brand = resultSet.getString("brand");
                String description = resultSet.getString("description");
                Double value = resultSet.getDouble("value");
                Product product = new Product(id,name, brand, description, value);
                System.out.println(product.toString());
                productFound.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(productFound.toString());
        return productFound;
    }

    @Override
    public Product getProductById(int id) {
        System.out.println("GIVEN id IS:"+ id);
        Product productFound = null;
        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM database_sep3.public.products WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.isBeforeFirst()){
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String brand = resultSet.getString("brand");
                    String description = resultSet.getString("description");
                    double value = resultSet.getDouble("value");

                    productFound = new Product(id,name,brand,description,value);
                    System.out.println("Product found: " + productFound.toString());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productFound;
    }

    @Override
    public void addProduct(Product product) {
        Product result = null;
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO database_sep3.public.products (name,brand,description, value) VALUES (?,?, ?, ?)",  PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, product.getName());
            statement.setString(2, product.getBrand());
            statement.setString(3, product.getDescription());
            statement.setDouble(4, product.getValue());
            statement.executeUpdate();

            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                result = new Product (keys.getInt(1), product.getName(), product.getBrand(), product.getDescription(), product.getValue());
            } else {
                throw new SQLException("No key generated");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProductById(int id) {
        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM database_sep3.public.products WHERE id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateProduct(Product product) {
        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE database_sep3.public.products SET name = ?, brand = ?, description = ?, value = ? WHERE id = ?");
            statement.setString(1, product.getName());
            statement.setString(2, product.getBrand());
            statement.setString(3, product.getDescription());
            statement.setDouble(4, product.getValue());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
