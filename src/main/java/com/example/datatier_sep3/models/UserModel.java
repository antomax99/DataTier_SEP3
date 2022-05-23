package com.example.datatier_sep3.models;

import java.io.IOException;
import java.util.List;

public interface UserModel {
    User getUserById(int id);
    void addUser(User user) ;
    void deleteUserById(int id);
    void updateUser(User user);
    User getUserByUsername(String username) throws IOException;
}

