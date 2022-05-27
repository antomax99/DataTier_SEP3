package com.example.datatier_sep3.daos;

import com.example.datatier_sep3.models.entities.Product;

import java.io.IOException;
import java.util.List;

public interface ProductDAO {

    List<Product> getAllProducts() throws IOException;
    Product getProductsById(int id);
    void addProducts(Product product) ;
    void deleteProductById(int id);
    void updateProduct(Product product);

}
