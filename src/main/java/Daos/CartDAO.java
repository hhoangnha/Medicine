/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Daos;

import DB.DBConnection;
import Model.BrandModel;
import Model.CartItem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nguyen Hoang Nha
 */
public class CartDAO {

    Connection conn;

    public CartDAO() {
        try {
            conn = DBConnection.connect();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BrandDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BrandDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet getAll(int customerID) {
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from Carts where CustomerID = " + customerID + "");
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(BrandDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<CartItem> getCartItems(int customerId) {
        List<CartItem> cartItems = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT ProID, Quantity FROM Carts WHERE CustomerID = ?");
            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt("ProID");
                int quantity = rs.getInt("Quantity");
                CartItem cartItem = new CartItem();
                cartItem.setCustomerID(customerId);
                cartItem.setProId(productId);
                cartItem.setQuantity(quantity);
                cartItems.add(cartItem);
            }

            System.out.println(cartItems);
        } catch (SQLException ex) {
            System.out.println("Failed to get cart items");
            ex.printStackTrace();
        }
        return cartItems;
    }

    public int addOrUpdateCartItem(int CustomerID, int ProID, int Quantity) {
        int count = 0;
        try {
            PreparedStatement checkPs = conn.prepareStatement("SELECT Quantity FROM Carts WHERE CustomerID = ? AND ProID = ?");
            checkPs.setInt(1, CustomerID);
            checkPs.setInt(2, ProID);
            ResultSet rs = checkPs.executeQuery();

            //có tồn tại trong db
            if (rs.next()) {
                int currentQuantity = rs.getInt("Quantity");
                int newQuantity = currentQuantity + Quantity;

                PreparedStatement updatePs = conn.prepareStatement("UPDATE Carts SET Quantity = ? WHERE CustomerID = ? AND ProID = ?");
                updatePs.setInt(1, newQuantity);
                updatePs.setInt(2, CustomerID);
                updatePs.setInt(3, ProID);

                count = updatePs.executeUpdate();
                System.out.println(count + " rows updated");
                System.out.println("Cart item updated successfully");
            } else {
                // chưa có thì thêm mới
                PreparedStatement insertPs = conn.prepareStatement("INSERT INTO Carts(CustomerID, ProID, Quantity) VALUES(?,?,?)");
                insertPs.setInt(1, CustomerID);
                insertPs.setInt(2, ProID);
                insertPs.setInt(3, Quantity);

                count = insertPs.executeUpdate();
                System.out.println("Cart item added successfully");
            }
        } catch (SQLException ex) {
            System.out.println("Failed to add/update cart item");
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public int removeCartItem(int CustomerID, int ProID) {
        int count = 0;
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Carts WHERE CustomerID = ? AND ProID = ?");
            ps.setInt(1, CustomerID);
            ps.setInt(2, ProID);

            count = ps.executeUpdate();
            System.out.println(count + " rows deleted");
            System.out.println("Cart item removed successfully");
        } catch (SQLException ex) {
            System.out.println("Failed to remove cart item");
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public int increaseOrDecreaseCartItemQuantity(int CustomerID, int ProID, int quantityChange) {
        int count = 0;
        try {
            PreparedStatement checkPs = conn.prepareStatement("SELECT Quantity FROM Carts WHERE CustomerID = ? AND ProID = ?");
            checkPs.setInt(1, CustomerID);
            checkPs.setInt(2, ProID);
            ResultSet rs = checkPs.executeQuery();

            if (rs.next()) {
                int currentQuantity = rs.getInt("Quantity");
                int newQuantity = currentQuantity + quantityChange;

                if (newQuantity > 0) {
                    // Nếu số lượng mới lớn hơn 0, cập nhật số lượng
                    PreparedStatement updatePs = conn.prepareStatement("UPDATE Carts SET Quantity = ? WHERE CustomerID = ? AND ProID = ?");
                    updatePs.setInt(1, newQuantity);
                    updatePs.setInt(2, CustomerID);
                    updatePs.setInt(3, ProID);

                    count = updatePs.executeUpdate();
                    System.out.println(count + " rows updated");
                    System.out.println("Cart item updated successfully");
                } else {
                    // Nếu số lượng mới là 0 hoặc âm, xoá sản phẩm khỏi giỏ hàng
                    PreparedStatement deletePs = conn.prepareStatement("DELETE FROM Carts WHERE CustomerID = ? AND ProID = ?");
                    deletePs.setInt(1, CustomerID);
                    deletePs.setInt(2, ProID);

                    count = deletePs.executeUpdate();
                    System.out.println(count + " rows deleted");
                    System.out.println("Cart item removed from cart due to zero quantity");
                }
            } else {
                System.out.println("Cart item not found for customer");
            }
        } catch (SQLException ex) {
            System.out.println("Failed to update cart item");
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

}
