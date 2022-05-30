package com.example.datatier_sep3.contracts;

import com.example.datatier_sep3.models.entities.Order;
import com.example.datatier_sep3.models.entities.Product;

import java.io.IOException;
import java.util.List;

public interface ProductModel {

        List<Product> getAllProducts() throws IOException;
        Product getProductById(int id);
        void addProduct(Product product);
        public void addProductToOrder(Product product, int orderId);
        void deleteProductById(int id);
        void updateProduct(Product product);

}

