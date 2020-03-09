package com.jy.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 连接 MySQL 数据库
 */
public class DbConn {
    public static Connection getConn(){
        String url = "jdbc:mysql://localhost:3306/shopping_system_01?&useSSL=false&serverTimezone=UTC";
        String user = "root";
        String password = "mysqlpsw";
        Connection connection = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
