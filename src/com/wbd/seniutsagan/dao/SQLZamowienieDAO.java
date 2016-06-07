package com.wbd.seniutsagan.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLZamowienieDAO implements ZamowienieDAO{
    private static final String DB_CONNECTION = "jdbc:oracle:thin:@ora3.elka.pw.edu.pl:1521:ora3inf";
    private static final String DB_USER = "DSAGAN";
    private static final String DB_PASSWORD = "dsagan";

    private static Connection getDBConnection() throws SQLException {
        return DriverManager.getConnection(DB_CONNECTION,DB_USER,DB_PASSWORD);
    }

    @Override
    public void pushOrder(ZamowienieDAO zamowienieDAO) {

    }
}
