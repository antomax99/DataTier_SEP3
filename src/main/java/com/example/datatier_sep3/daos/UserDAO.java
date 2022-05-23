package com.example.datatier_sep3.daos;

import com.example.datatier_sep3.models.User;

import java.io.IOException;
import java.util.List;

public interface UserDAO {

    User getUserById(int id);
    void addUser(User user) ;
    void deleteUserById(int id);
    void updateUser(User user);
    User getUserByUsername(String username) throws IOException;
}
