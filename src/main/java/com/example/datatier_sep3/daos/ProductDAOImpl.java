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
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/database_sep3", "postgres", "1234");
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
    public Product getProductsById(int id) {
        return null;
    }

    @Override
    public void addProducts(Product product) {

    }

    @Override
    public void deleteProductById(int id) {

    }

    @Override
    public void updateProduct(Product product) {

    }
}
