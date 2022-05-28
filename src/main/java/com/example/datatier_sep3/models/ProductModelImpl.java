package com.example.datatier_sep3.models;

import com.example.datatier_sep3.contracts.ProductModel;
import com.example.datatier_sep3.daos.ProductDAOImpl;
import com.example.datatier_sep3.daos.UserDAOImpl;
import com.example.datatier_sep3.models.entities.Product;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ProductModelImpl implements ProductModel {

    private ProductDAOImpl productDAO;
    {
        try {
            productDAO = ProductDAOImpl.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Product> getAllProducts() throws IOException {
        return productDAO.getAllProducts();
    }

    @Override
    public Product getProductById(int id) {
        return productDAO.getProductById(id);
    }

    @Override
    public void addProduct(Product product) {
        productDAO.addProduct(product);
    }

    @Override
    public void deleteProductById(int id) {
        productDAO.deleteProductById(id);
    }

    @Override
    public void updateProduct(Product product) {
        productDAO.updateProduct(product);
    }
}
