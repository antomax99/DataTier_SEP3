package com.example.datatier_sep3.daos;

import com.example.datatier_sep3.models.entities.User;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private static UserDAOImpl instance;

    private UserDAOImpl() throws SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
    }

    public static synchronized UserDAOImpl getInstance() throws SQLException {
        if (instance == null) {
            instance = new UserDAOImpl();
        }
        return instance;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/database_sep3", "postgres", "123456");
    }


    @Override
    public List<User> getAllUsers() throws IOException {
        List<User> usersFound = new ArrayList<>();
        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM database_sep3.public.users WHERE security_level = ?");
            statement.setInt(1, 1);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String userName = resultSet.getString("username");
                String password = resultSet.getString("password");
                String firstname = resultSet.getString("first_name");
                String lastname = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                int securityLevel = resultSet.getInt("security_level");
                User user = new User(id,userName,password,firstname,lastname,email,securityLevel);
                System.out.println(user.toString());
                usersFound.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(usersFound.toString());
        return usersFound;
    }

    @Override
    public User getUserById(int id) {
        User userFound = null;
        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM database_sep3.public.users WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.isBeforeFirst()){
                if (resultSet.next()) {
                    String userName = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    String firstname = resultSet.getString("first_name");
                    String lastname = resultSet.getString("last_name");
                    String email = resultSet.getString("email");
                    int securityLevel = resultSet.getInt("security_level");
                    userFound = new User(id,userName,password,firstname,lastname,email,securityLevel);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userFound;
    }


    @Override
    public void addUser(User user) {
        User result = null;
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO database_sep3.public.users (username,password,first_name, last_name, email, security_level) VALUES (?,?, ?, ?, ?, ?)",  PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setString(5, user.getEmail());
            statement.setInt(6, user.getSecurityLevel());
            statement.executeUpdate();

            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                result = new User (keys.getInt(1), user.getUserName(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getSecurityLevel());
            } else {
                throw new SQLException("No key generated");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUserById(int id) {
        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM database_sep3.public.users WHERE id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateUser(User user) {
        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE database_sep3.public.users SET username = ?, password = ?, first_name = ?, last_name = ?, email = ? WHERE id = ?");
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setString(5,user.getEmail());
            statement.setInt(6,user.getUserId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUserByUsername(String username) throws IOException {
        User userFound = null;
        System.out.println(username);
        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM database_sep3.public.users WHERE username = ?");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.isBeforeFirst()){
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String userName = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    String firstname = resultSet.getString("first_name");
                    String lastname = resultSet.getString("last_name");
                    String email = resultSet.getString("email");
                    int securityLevel = resultSet.getInt("security_level");
                    userFound = new User(id,userName,password,firstname,lastname,email,securityLevel);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(userFound.toString());
        return userFound;
    }
}
