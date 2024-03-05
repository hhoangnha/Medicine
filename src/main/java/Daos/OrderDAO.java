/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Daos;

import DB.DBConnection;
import static Daos.UserDAO.getMd5;
import Model.CartItem;
import Model.OrderModel;
import Model.UserModel;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Mode
 *
 * @author Nguyen Hoang Nha - CE170092
 */
public class OrderDAO {

    Connection conn;

    public OrderDAO() {
        try {
            conn = DBConnection.connect();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ResultSet getAllByUsername(String username) {
        ResultSet rs = null;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Orders WHERE Orders.Username=?");
            ps.setString(1, username);
            rs = ps.executeQuery();
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int createNewOrder(int id, String date, String username, int total, int status, String address, String phone, String note, List carts) {
        int idNew = 0;
        try {
            Statement st = conn.createStatement();
            PreparedStatement ps = conn.prepareStatement("insert into Orders OUTPUT inserted.OrderID values(?,?,?,?,?,?,?) ");
            ps.setString(1, date);
            ps.setString(2, username);
            ps.setInt(3, total);
            ps.setInt(4, status);
            ps.setString(5, address);
            ps.setString(6, phone);
            ps.setString(7, note);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                idNew = rs.getInt("OrderID"); // Lấy ID vừa được chèn

                for (CartItem item : (List<CartItem>) carts) {
//                    PreparedStatement psDt = conn.prepareStatement("insert into OrderDetails values(?,?,?) ");
//                    psDt.setInt(1, idNew);
//                    psDt.setInt(2, item.getProductId());
//                    psDt.setInt(3, item.getQuantity());
//                    psDt.executeUpdate();
//
//                    PreparedStatement upProduct = conn.prepareStatement("UPDATE Products SET Quantity = Quantity - ? WHERE ProID = ?");
//                    upProduct.setInt(1, item.getQuantity());
//                    upProduct.setInt(2, item.getProductId());
//                    upProduct.executeUpdate();

                }
                return idNew;
            }
//           data = new OrderModel(idNew, date, username, total, status, address, phone, note);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idNew;
    }

    public boolean checkQuanProduct(int proid, int quan) {
        boolean check = false;
        try {
            Statement st = conn.createStatement();

            PreparedStatement ps = conn.prepareStatement("SELECT *  FROM Products WHERE ProID = ? AND Quantity >= ?");
            ps.setInt(1, proid);
            ps.setInt(2, quan);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                check = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }  
    public ResultSet getAll() {
        ResultSet rs = null;
        try {
            Statement st = conn.createStatement(); // tạo đối tượng thực thi câu lệnh
            PreparedStatement ps = conn.prepareStatement("select * from Orders");
            rs = ps.executeQuery();
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    public void delete(int OrderID){
        try {
            PreparedStatement ps = conn.prepareStatement("Delete from Orders where OrderID = ?");
            ps.setInt(1, OrderID);
            int count = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UnitDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public int updateStatus(int OrderID, int orderStatus) {
        int count = 0;
        try {
            PreparedStatement ps = conn.prepareStatement("update Orders set orderStatus=? where OrderID=?");
//            ps.setString(1, newinfo.getUsername());
            ps.setInt(1, orderStatus);
            ps.setInt(2, OrderID);
            count = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (count == 0) ? 0 : 1;
    }
}
