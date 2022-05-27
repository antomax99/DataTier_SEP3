package com.example.datatier_sep3.networking;

import com.example.datatier_sep3.contracts.OrderModel;
import com.example.datatier_sep3.contracts.ProductModel;
import com.example.datatier_sep3.contracts.UserModel;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable{




    private final int port1 = 2911;
    private final int port2 = 2912;
    private final int port3 = 2913;

    private ServerSocket serverSocket1;
    private ServerSocket serverSocket2;
    private ServerSocket serverSocket3;

    private UserModel userModel;
    private OrderModel orderModel;
    private ProductModel productModel;


    public Server(UserModel userModel, OrderModel orderModel, ProductModel productModel){
        this.userModel = userModel;
        this.orderModel= orderModel;
        this.productModel = productModel;

        try {
            serverSocket1 = new ServerSocket(port1);
            serverSocket2 = new ServerSocket(port2);
            serverSocket3 = new ServerSocket(port3);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(true){
            try {
                Socket socket1 = serverSocket1.accept();
                Socket socket2 = serverSocket2.accept();
                Socket socket3 = serverSocket3.accept();
                System.out.println("client connected");
                Thread th1 = new Thread(new UserSocketHandler(socket1, userModel));
                th1.start();
                Thread th2 = new Thread(new OrderSocketHandler(socket2, orderModel));
                th2.start();
                Thread th3 = new Thread(new ProductSocketHandler(socket3, productModel));
                th3.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
