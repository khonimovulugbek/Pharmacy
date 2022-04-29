package com.example.backend.host;

import java.sql.*;
import java.util.Scanner;

public abstract class Host {
    public static final Scanner scanner = new Scanner(System.in);
    public static Connection connection;
    public static Statement statement;
    public static PreparedStatement drugSt;

    static {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "qwerty");
            statement = connection.createStatement();
            drugSt = connection.prepareStatement("insert into getDrugs(name, type, dose, manufactured, cost, amount)\n" +
                    "VALUES (?, ?, ?, ?, ?, ?);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

