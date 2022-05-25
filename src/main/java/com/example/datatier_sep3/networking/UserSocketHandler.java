package com.example.datatier_sep3.networking;

import com.example.datatier_sep3.models.entities.User;
import com.example.datatier_sep3.contracts.UserModel;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class UserSocketHandler implements Runnable {


    private Gson gson;
    private Socket socket;
    private UserModel userModel;
    private BufferedReader in;
    private PrintWriter out;


    public UserSocketHandler(Socket socket, UserModel userModel) throws IOException {
        this.socket = socket;
        this.userModel = userModel;
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
            case "get users":
                getUsers();
                break;
            case "add user":
                saveUser();
                break;
            case "get user by username":
                getUserByUsername();
                break;
            case "get user by id":
                getUserById();
                break;
            case "delete user by id":
                deleteUserById();
                break;
            case "update user":
                updateUser();
                break;
        }
    }

    private void getUsers() throws IOException {
        List<User> usersFound = userModel.getAllUsers();
        String userAsJson = gson.toJson(usersFound);
        out.println(userAsJson);
    }

    private void saveUser() {
        System.out.println("SAVING USER");
        try {
            String request = in.readLine();
            User user = gson.fromJson(request, User.class);
            System.out.println(user.toString() + " this user is saving.");
            userModel.addUser(user);
        } catch (IOException e) {
            out.println("Error: User not saved");
        }
    }

    private void getUserById() {
        try {
            String request = in.readLine();
            int id = gson.fromJson(request, Integer.class);
            User userFound =  userModel.getUserById(id);
            String userAsJson = gson.toJson(userFound);
            out.println(userAsJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateUser() throws IOException {
        String request = in.readLine();
        User user = gson.fromJson(request, User.class);
        userModel.updateUser(user);
    }

    private void deleteUserById() throws IOException {
        String request = in.readLine();
        int id = gson.fromJson(request,Integer.class);
        userModel.deleteUserById(id);
    }

    private void getUserByUsername() throws IOException {
        String request = in.readLine();
        String username = gson.fromJson(request,String.class);
        User userFound = userModel.getUserByUsername(username);
        String userAsJson = gson.toJson(userFound);
        out.println(userAsJson);

    }
}
