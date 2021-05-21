package com.zahar.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBC {
    static final String JDBC_Driver = "com.mysql.cj.jdbc.Driver";
    static final String DataBase_URL = "jdbc:mysql://localhost:3306/mydbtest";
    static final String User = "root";
    static final String Password = "Bnm765iop765";


    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try {
            Class.forName(JDBC_Driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Connection connection = DriverManager.getConnection(DataBase_URL, User, Password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Book");
            while (resultSet.next()) {
                list.add(new User(resultSet.getInt("id"), resultSet.getString("AuthorName"),
                        resultSet.getString("AuthorLastName"),
                        resultSet.getString("BookName")));
            }
            resultSet.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int addUser(User user) {
        try {
            Class.forName(JDBC_Driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Connection connection = DriverManager.getConnection(DataBase_URL, User, Password);
            String sql = "INSERT INTO Book (id,AuthorName,AuthorLastName,BookName) Value(?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getAuthorName());
            preparedStatement.setString(3, user.getAuthorLastName());
            preparedStatement.setString(4, user.getBookName());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int deleteUserById(int id) {

        try {
            Class.forName(JDBC_Driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Connection connection = DriverManager.getConnection(DataBase_URL, User, Password);
            String sql = "DELETE FROM Book WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
