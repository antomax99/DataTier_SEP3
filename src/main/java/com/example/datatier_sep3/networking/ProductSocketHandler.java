package com.example.datatier_sep3.networking;

import com.example.datatier_sep3.contracts.ProductModel;
import com.example.datatier_sep3.models.entities.Product;
import com.example.datatier_sep3.models.entities.User;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ProductSocketHandler implements Runnable {
    private Gson gson;
    private Socket socket;
    private ProductModel productModel;
    private BufferedReader in;
    private PrintWriter out;


    public ProductSocketHandler(Socket socket, ProductModel productModel) throws IOException {
        this.socket = socket;
        this.productModel = productModel;
        gson = new Gson();
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    @Override
    public void run() {
        try {
            while(true) {
                System.out.println("WAITING FOR REQUEST");
                String req = in.readLine();
                System.out.println("REQUEST <====> " + req);
                doThisMethod(req);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doThisMethod(String method) throws IOException {
        System.out.println("method <====> " + method);
        switch(method) {
            case "get products":
                getProducts();
                break;
            case "add product":
                saveProduct();
                break;
            case "get product by id":
                getProductById();
                break;
            case "delete product by id":
                deleteProductById();
                break;
            case "update product":
                updateProduct();
                break;
        }
    }

    private void getProducts() throws IOException {
        List<Product> ProductsFound = productModel.getAllProducts();
        String productAsJson = gson.toJson(ProductsFound);
        out.println(productAsJson);
    }

    private void saveProduct() {
        try {
            String request = in.readLine();
            Product product = gson.fromJson(request, Product.class);
            System.out.println(product.toString() + " saving this product.");
            productModel.addProduct(product);
        } catch (IOException e) {
            out.println("Error: Product not saved");
        }
    }

    private void getProductById() {

        try {
            String request = in.readLine();
            int id = gson.fromJson(request, Integer.class);
            Product productFound =  productModel.getProductById(id);
            String productAsJson = gson.toJson(productFound);
            out.println(productAsJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateProduct() throws IOException {
        String request = in.readLine();
        Product product = gson.fromJson(request, Product.class);
        productModel.updateProduct(product);
    }

    private void deleteProductById() throws IOException {
        String request = in.readLine();
        int id = gson.fromJson(request,Integer.class);
        productModel.deleteProductById(id);
    }

}
