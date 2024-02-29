/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author Nguyen Hoang Nha - CE170092
 */
public class DBConnection {
//    public static Connection connect() throws ClassNotFoundException, SQLException{
//        Class.forName("com.mysql.cj.jdbc.Driver");
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:8889/Medicine?user=root&password=root");
////            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Medicine;user=sa;password=12345;encrypt=true;trustServerCertificate=true");
//        return conn;
//        String host = "jdbc:mysql://localhost:8889/Medicine";
//        String username = "root";
//        String password = "root";
//        try {
//            Connection conn = DriverManager.getConnection(host, username, password);
////            System.out.println("Connected to MySQL database");
//            return conn;
//        } catch (SQLException e) {
//            System.out.println("Failed to connect to MySQL database");
//            e.printStackTrace();
//        }
//        return null;
//        
//    }
    public static Connection connect() throws ClassNotFoundException, SQLException{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Medicine;user=sa;password=admin;encrypt=true;trustServerCertificate=true");
        return conn;
    }
}
