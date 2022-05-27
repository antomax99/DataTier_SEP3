package com.example.datatier_sep3.networking;

import com.example.datatier_sep3.contracts.OrderModel;
import com.example.datatier_sep3.models.entities.Order;
import com.example.datatier_sep3.models.entities.User;
import com.google.gson.Gson;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.io.PrintWriter;
        import java.net.Socket;
        import java.util.List;

public class OrderSocketHandler implements Runnable {


    private Gson gson;
    private Socket socket;
    private OrderModel orderModel;
    private BufferedReader in;
    private PrintWriter out;


    public OrderSocketHandler(Socket socket, OrderModel orderModel) throws IOException {
        this.socket = socket;
        this.orderModel = orderModel;
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
                System.out.println("REQUEST ===> " + req);
                doThisMethod(req);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doThisMethod(String method) throws IOException {
        System.out.println("method ===> " + method);
        switch(method) {
            case "get orders":
                getOrders();
                break;
            case "add order":
                saveOrders();
                break;
            case "get order by id":
                getOrderById();
                break;
            case "delete order by id":
                deleteOrderById();
                break;
            case "update order":
                updateOrders();
                break;
        }
    }

    private void getOrders() throws IOException {
        List<Order> ordersFound = orderModel.getAllOrders();
        String userAsJson = gson.toJson(ordersFound);
        out.println(userAsJson);
    }

    private void saveOrders() {
        System.out.println("SAVING USER");
        try {
            String request = in.readLine();
            Order order = gson.fromJson(request, Order.class);
            System.out.println(order.toString() + " this user is saving.");
            orderModel.addOrder(order);
        } catch (IOException e) {
            out.println("Error: User not saved");
        }
    }

    private void getOrderById() {
        try {
            String request = in.readLine();
            int id = gson.fromJson(request, Integer.class);
            Order orderFound =  orderModel.getOrderById(id);
            String userAsJson = gson.toJson(orderFound);
            out.println(userAsJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateOrders() throws IOException {
        String request = in.readLine();
        Order order  = gson.fromJson(request, Order.class);
        orderModel.updateOrder(order);
    }

    private void deleteOrderById() throws IOException {
        String request = in.readLine();
        int id = gson.fromJson(request,Integer.class);
        orderModel.deleteOrderById(id);
    }
}
