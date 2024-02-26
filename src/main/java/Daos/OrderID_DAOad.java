/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Daos;

import DB.DBConnection;
import Model.OrderModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import xuly.md5;

/**
 *
 * @author Dell
 */
public class OrderID_DAOad {

    Connection conn;
    xuly.md5 v = new md5();

    public OrderID_DAOad() throws ClassNotFoundException {
        try {
            conn = DBConnection.connect();
        } catch (SQLException ex) {
            Logger.getLogger(OrderID_DAOad.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ResultSet getAll() {
        ResultSet rs = null;
        try {

            Statement st = conn.createStatement();
            rs = st.executeQuery("select * from orders JOIN Users ON Users.Username = Orders.Username");
        } catch (SQLException ex) {
            Logger.getLogger(OrderID_DAOad.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ResultSet getProductOrder(int orid) {
        ResultSet rs = null;
        try {

            Statement st = conn.createStatement();
            rs = st.executeQuery("select *,OrderDetails.Quantity as OrderDetailQuan  from OrderDetails JOIN Orders ON Orders.OrderID = OrderDetails.OrderID \n"
                    + "JOIN Users ON Users.Username = Orders.Username\n"
                    + "JOIN Products ON Products.ProID = OrderDetails.ProID Where OrderDetails.OrderID =" + orid);
        } catch (SQLException ex) {
            Logger.getLogger(OrderID_DAOad.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public int addQuanToProduct(int proQuan, int proID){
        int res = 0;
        try {
             PreparedStatement upProduct = conn.prepareStatement("UPDATE Products SET Quantity = Quantity + ? WHERE ProID = ?");
                    upProduct.setInt(1, proQuan);
                    upProduct.setInt(2, proID);
                    upProduct.executeUpdate();
                    res=1;
        } catch (Exception e) {
             Logger.getLogger(OrderID_DAOad.class.getName()).log(Level.SEVERE, null, e);
            res=0;
        }
        return res;
    }

    public OrderModel getOrder(int pro_id) {
        OrderModel pr = null;
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Orders where OrderID =?");
            ps.setInt(1, pro_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                pr = new OrderModel(rs.getInt("OrderID"), rs.getString("OrderDate"), rs.getString("Username"), rs.getInt("OrderTotal"), rs.getInt("OrderStatus"), rs.getString("OrderAddress"), rs.getString("OrderPhone"), rs.getString("OrderNote"));

                //public OrderModel(int OrderID, String OrderDate, String Username, int OrderTotal, int OrderStatus, String OrderAddress, String OrderPhone, String OrderNote)
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderID_DAOad.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pr;
    }

    public int update(int oid, int st) {
        int count = 0;
        int currentStatus = 0; // Giá trị ban đầu cho OrderStatus

        try {
            // Truy vấn để lấy giá trị hiện tại của OrderStatus
            PreparedStatement selectPs = conn.prepareStatement("SELECT OrderStatus FROM Orders WHERE OrderID = ?");
            selectPs.setInt(1, oid);
            ResultSet rs = selectPs.executeQuery();

            if (rs.next()) {
                currentStatus = rs.getInt("OrderStatus");
            }

            // Kiểm tra các điều kiện và quyết định cập nhật
//            if (currentStatus > 1 && st == 0) {
//                // Không thể chuyển từ ngoài 1 về 0 vì khi mà đã đóng gói r thì ko shop nào cho hủy cả t mua t biết
//                count = 0;
//            } else {
                // Cập nhật OrderStatus
                PreparedStatement updatePs = conn.prepareStatement("UPDATE Orders SET OrderStatus = ? WHERE OrderID = ?");
                updatePs.setInt(1, st);
                updatePs.setInt(2, oid);
                count = updatePs.executeUpdate();
//            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderID_DAOad.class.getName()).log(Level.SEVERE, null, ex);
        }

        return (count == 0) ? 0 : 1;
    }

//    public orr getOrder_id(String order_id) {
//        orr ord = null;
//        try {
//
//            PreparedStatement ps = conn.prepareStatement("select * from [Order] where order_id=?");
//            ps.setString(1, order_id);
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                ord = new orr(rs.getString("order_id"), rs.getString("username"), rs.getInt("order_total"), rs.getDate("order_date"), rs.getString("order_date"));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(OrderDAOS.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return ord;
//    }
}
