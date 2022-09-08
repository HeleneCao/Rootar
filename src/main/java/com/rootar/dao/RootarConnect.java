package com.rootar.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class RootarConnect {
    private static Connection connexion;

    private RootarConnect() {

    }

    public static Connection getInstance() {
        if (connexion == null) {
            try {
                String dbURL = "jdbc:sqlserver://10.115.58.232:1401;databaseName=ROOTAR;encrypt=false";
                String user = "sa";
                String pass = "azerty@123456";
                connexion = DriverManager.getConnection(dbURL, user, pass);
            }

            // Handle any errors that may have occurred.
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return connexion;
    }
}