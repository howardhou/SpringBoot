package com.example.mybatisdemo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

// JDBC 访问数据库
public class JdbcDao {

    private static Connection getConnection() {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/test";
        String username = "root";
        String password = "123456";
        Connection connection = null;

        try {
            Class.forName(driver); //classLoader,加载对应驱动
            connection = (Connection) DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static Integer getAll() {
        Connection connection = getConnection();
        String sql = "select * from tb_user";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = (PreparedStatement)connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("============================");

            int columnCount = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(resultSet.getString(i) + "\t");
                    if ((i == 2) && (resultSet.getString(i).length() < 8)) {
                        System.out.print("\t");
                    }
                }
                System.out.println("");
            }
            System.out.println("============================");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
