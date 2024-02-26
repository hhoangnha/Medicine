/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Daos;

import DB.DBConnection;
import Model.UserModel;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Mode
 *
 * @author Nguyen Hoang Nha - CE170092
 */
public class AdminDao {

    Connection conn;

    public AdminDao() {
        try {
            conn = DBConnection.connect();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ResultSet countCustomer() {
        ResultSet rs = null;
//        try {
//            PreparedStatement ps = conn.prepareStatement("SELECT COUNT( Username) AS TotalCustomers FROM Users");
//
//            rs = ps.executeQuery();
//            return rs;
//        } catch (SQLException ex) {
//            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
//        }

        return rs;
    }

    public ResultSet countOrder(int month) {
        ResultSet rs = null;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT\n"
                    + "    COUNT(OrderID) AS TotalOrder,\n"
                    + "    SUM(CASE WHEN OrderStatus = 0 THEN 1 ELSE 0 END) AS CanceledOrders,\n"
                    + "    SUM(CASE WHEN OrderStatus = 1 THEN 1 ELSE 0 END) AS NewOrders,\n"
                    + "    SUM(CASE WHEN OrderStatus = 4 THEN 1 ELSE 0 END) AS DeliveredOrders\n"
                    + "FROM Orders\n"
                    + "WHERE Orders.OrderDate >= DATEADD(MONTH, -?, GETDATE())");
            ps.setInt(1, month);
            rs = ps.executeQuery();
            return rs;

        } catch (SQLException ex) {
            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ResultSet countTotalOrder(int month) {
//        ResultSet rs = null;
//        try {
//            PreparedStatement ps = conn.prepareStatement("SELECT SUM(OrderTotal) AS TotalRevenue\n"
//                    + "FROM Orders\n"
//                    + "WHERE OrderDate >= DATEADD(MONTH, -?, GETDATE()) AND OrderStatus = 4");
//            ps.setInt(1, month);
//            rs = ps.executeQuery();
//            return rs;
//
//        } catch (SQLException ex) {
//            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return null;
    }

    public ResultSet topProduct(int month) {
//        ResultSet rs = null;
//        try {
//            PreparedStatement ps = conn.prepareStatement("SELECT Products.ProID, Products.ProName,Categories.CateName, SUM(OrderDetails.Quantity) AS TotalSold\n"
//                    + "FROM OrderDetails\n"
//                    + "JOIN Orders ON OrderDetails.OrderID = Orders.OrderID\n"
//                    + "JOIN Products ON OrderDetails.ProID = Products.ProID\n"
//                    + "JOIN Categories ON Products.CateID = Categories.CateID\n"
//                    + "GROUP BY Products.ProID, Products.ProName, Categories.CateName\n"
//                    + "ORDER BY TotalSold DESC");
//            ps.setInt(1, month);
//            rs = ps.executeQuery();
//            return rs;
//
//        } catch (SQLException ex) {
//            Logger.getLogger(AdminDao.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return null;
    }

}
