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
import java.util.ArrayList;
import static java.util.Collections.list;
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

    public ResultSet getAllOrderByUserID(int UserID) {
//        List<OrderModel> userOrders = new ArrayList<>();
        ResultSet rs = null;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT\n"
                    + "    o.OrderID,\n"
                    + "    o.OrderDate,\n"
                    + "    SUM(up.Price * od.Quantity) AS Total,\n"
                    + "    o.OrderStatus\n"
                    + "FROM\n"
                    + "    Orders o\n"
                    + "JOIN \n"
                    + "    Accounts acc ON acc.UserID = o.CustomerID\n"
                    + "JOIN\n"
                    + "    OrderDetails od ON od.OrderID = o.OrderID\n"
                    + "JOIN\n"
                    + "    Products p ON p.ProID = od.ProID\n"
                    + "JOIN\n"
                    + "    Units u ON u.UnitID = od.UnitID\n"
                    + "JOIN\n"
                    + "    UnitProduct up ON up.ProID = od.ProID AND up.UnitID = od.UnitID\n"
                    + "WHERE\n"
                    + "    o.CustomerID = ?\n"
                    + "GROUP BY\n"
                    + "    o.OrderID, o.OrderDate, o.OrderStatus;");
            ps.setInt(1, UserID);
            rs = ps.executeQuery();
//            while (rs.next()) {
//                userOrders.add(new OrderModel(rs.getInt(1), rs.getString(4), rs.getString(7), rs.getInt(6), rs.getInt(5), rs.getString(8), rs.getString(9), rs.getString(9)));
//            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public int addNewOrder(int customerID, int staffID, String orderDate, int orderStatus, int total, String[] selectCarts) {
        int result = 0;
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Orders(CustomerID, StaffID, OrderDate, OrderStatus, Total) VALUES(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, customerID);
            ps.setInt(2, staffID);
            ps.setString(3, orderDate);
            ps.setInt(4, orderStatus);
            ps.setDouble(5, total);

            int count = ps.executeUpdate();

            if (count > 0) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int orderID = generatedKeys.getInt(1);
                    System.out.println("ID của order mới là ");
                    System.out.println(orderID);

                    for (String selectCart : selectCarts) {
                        int cartID = Integer.parseInt(selectCart);
                        // get product by cartiD
                        PreparedStatement psCart = conn.prepareStatement("SELECT ProID, UnitID, Quantity FROM Carts WHERE CartID = ?");
                        psCart.setInt(1, cartID);
                        ResultSet rsCart = psCart.executeQuery();

                        if (rsCart.next()) {
                            int proID = rsCart.getInt("ProID");
                            int unitID = rsCart.getInt("UnitID");
                            int quantity = rsCart.getInt("Quantity");
                            // add detail to OrderDetail
                            PreparedStatement psOrderDetail = conn.prepareStatement("INSERT INTO OrderDetails(OrderID, ProID, UnitID, Quantity) VALUES(?, ?, ?, ?)");
                            psOrderDetail.setInt(1, orderID);
                            psOrderDetail.setInt(2, proID);
                            psOrderDetail.setInt(3, unitID);
                            psOrderDetail.setInt(4, quantity);
                            // Thực hiện chèn dữ liệu
                            psOrderDetail.executeUpdate();

                            // delete cart when create success
                            PreparedStatement psDeleteCart = conn.prepareStatement("DELETE FROM Carts WHERE CartID = ?");
                            psDeleteCart.setInt(1, cartID);
                            psDeleteCart.executeUpdate();
                        }
                    }

//                    newOrder = new OrderModel(orderID, customerID, staffID, orderDate, orderStatus, total);
                }
            }
            result = 1;
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
            result = 0;
        }
        return result;
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

    public void delete(int OrderID) {
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

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        OrderDAO d = new OrderDAO();
        ResultSet rs = d.getAllOrderByUserID(6);
        while (rs.next()) {
            OrderID_DAOad dod = new OrderID_DAOad();
            System.out.println("START WHILE1 ORDERID:" + rs.getInt("OrderID"));
            System.out.println(">>>"+rs.getInt("OrderID"));
            ResultSet rsc = dod.getProductOrder(rs.getInt("OrderID"));
            while (rsc.next()) {
                System.out.println("WHILE2 ORDERID:" + rs.getInt("OrderID"));
            }
            if (rs.getInt("OrderStatus") == 1) {
                System.out.println("Cancel Order");
            }
            System.out.println("END WHILE1 ORDERID:" + rs.getInt("OrderID"));
        }
    }
}
