package com.yberdaliyev.models.connectors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Connector {
    private static final String USER = "postgres";//Логин пользователя
    private static final String PASSWORD = "postgres";//Пароль пользователя
    private static final String URL = "jdbc:postgresql://localhost:5432/HomeWork2";//URL адрес
    private static final String DRIVER = "org.postgresql.Driver";//Имя драйвера


    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Properties p=new Properties();
        p.put("user",USER);
        p.put("password",PASSWORD);
        p.put("characterEncoding","UTF-8");
        return DriverManager.getConnection(URL, p);
    }
}
