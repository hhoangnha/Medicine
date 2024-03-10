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
            rs = st.executeQuery("SELECT od.OdID, p.ProName, od.Quantity, u.UnitID, up.Price*od.Quantity as total_price FROM OrderDetails od\n"
                    + "JOIN Products p on p.ProID = od.ProID\n"
                    + "JOIN Units u on u.UnitID = od.UnitID\n"
                    + "JOIN UnitProduct up on up.ProID = od.ProID and up.UnitID = od.UnitID\n"
                    + "WHERE OrderID = " + orid);
        } catch (SQLException ex) {
            Logger.getLogger(OrderID_DAOad.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public int addQuanToProduct(int proQuan, int proID) {
        int res = 0;
        try {
            PreparedStatement upProduct = conn.prepareStatement("UPDATE Products SET Quantity = Quantity + ? WHERE ProID = ?");
            upProduct.setInt(1, proQuan);
            upProduct.setInt(2, proID);
            upProduct.executeUpdate();
            res = 1;
        } catch (Exception e) {
            Logger.getLogger(OrderID_DAOad.class.getName()).log(Level.SEVERE, null, e);
            res = 0;
        }
        return res;
    }

    public ResultSet getUserOrderInfor(int orderID) {
        ResultSet rs;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT acc.Address, o.OrderDate, acc.Username, acc.Phone, o.OrderStatus FROM Orders o\n"
                    + "JOIN Accounts acc ON acc.UserID = o.CustomerID\n"
                    + "WHERE o.OrderID = ?");
            ps.setInt(1, orderID);
            rs = ps.executeQuery();
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(OrderID_DAOad.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ResultSet getListOrderProduct(int orderID) {
        ResultSet rs = null;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT up.Price * od.Quantity AS Total,\n"
                    + "p.ProName, od.Quantity, u.UnitName FROM Orders o \n"
                    + "JOIN OrderDetails od ON od.OrderID = o.OrderID\n"
                    + "JOIN Products p ON p.ProID = od.ProID\n"
                    + "JOIN Units u ON u.UnitID = od.UnitID\n"
                    + "JOIN UnitProduct up ON up.ProID = od.ProID AND up.UnitID = od.UnitID\n"
                    + "WHERE o.OrderID = ?");
            ps.setInt(1, orderID);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(OrderID_DAOad.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
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
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        OrderID_DAOad od = new OrderID_DAOad();
        ResultSet userOrderInfor = od.getUserOrderInfor(2);
        if (userOrderInfor.next()) {
            String account = userOrderInfor.getString("Username");
            System.out.println(account);
        }

    }
}
