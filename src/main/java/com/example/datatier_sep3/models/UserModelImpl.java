package com.example.datatier_sep3.models;


import com.example.datatier_sep3.contracts.UserModel;
import com.example.datatier_sep3.daos.UserDAOImpl;
import com.example.datatier_sep3.models.entities.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UserModelImpl implements UserModel {

    private UserDAOImpl userDAO;
    {
        try {
            userDAO = UserDAOImpl.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<User> getAllUsers() throws IOException {
        return userDAO.getAllUsers();
    }

    @Override
    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }

    @Override
    public void addUser(User user) {
        userDAO.addUser(user);
    }

    @Override
    public void deleteUserById(int id) {
        userDAO.deleteUserById(id);
    }

    @Override
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    @Override
    public User getUserByUsername(String username) throws IOException {
        return userDAO.getUserByUsername(username);
    }
}
