/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Administrator
 */
public class DBConnectionPG {
    
     public static final String url = "jdbc:postgresql://192.168.169.136:5500/evarsity_2022_04_07"; 
    public static final String userid = "postgres";
    public static final String password = "fiplpostgres";
    public static final String driver="org.postgresql.Driver";

    
    public Connection connect() {
        Connection conn = null;
        try {
             Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, userid, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } 
        catch (ClassNotFoundException e) {
            System.out.println("DB connection failed "+e.getMessage());
        }
        catch (SQLException e) {
            System.out.println("DB connection failed "+e.getMessage());
        }

        return conn;
    }
    
    public static void main(String args[]){
        System.out.println("Connection status ");
        new DBConnectionPG().connect();
    }
    
}
