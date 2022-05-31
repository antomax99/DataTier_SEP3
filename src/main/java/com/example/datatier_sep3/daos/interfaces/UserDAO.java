package com.example.datatier_sep3.daos.interfaces;

import com.example.datatier_sep3.entities.User;

import java.io.IOException;
import java.util.List;

public interface UserDAO {
    List<User> getAllUsers() throws IOException;
    User getUserById(int id);
    void addUser(User user) ;
    void deleteUserById(int id);
    void updateUser(User user);
    User getUserByUsername(String username) throws IOException;
}
