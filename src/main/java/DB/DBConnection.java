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
    public static Connection connect() throws ClassNotFoundException, SQLException{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Medicine;user=sa;password=12345;encrypt=true;trustServerCertificate=true");
        return conn;
    }
}

