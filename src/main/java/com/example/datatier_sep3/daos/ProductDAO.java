package com.example.datatier_sep3.daos;

import com.example.datatier_sep3.models.entities.Product;

import java.io.IOException;
import java.util.List;

public interface ProductDAO {

    List<Product> getAllProducts() throws IOException;
    Product getProductById(int id);
    void addProduct(Product product) ;
    public void addProductToOrder(Product product, int orderId);
    void deleteProductById(int id);
    void updateProduct(Product product);

}
