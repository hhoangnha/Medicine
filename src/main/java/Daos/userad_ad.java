/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Daos;

import DB.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
//import model.user;
import Model.UserModel;
import xuly.md5;

/**
 *
 * @author Dell
 */
public class userad_ad {

    Connection conn;
    xuly.md5 v = new md5();

    public userad_ad() {
        try {
            conn = DBConnection.connect();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(userad_ad.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(userad_ad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    public ResultSet getAll(String usertype, int st) {
        ResultSet rs = null;
        try {
            // Create a PreparedStatement with a parameterized query
            String sql = "SELECT * FROM Accounts";
            PreparedStatement ps = conn.prepareStatement(sql);
            // ps.setString(1, usertype);
            // ps.setInt(2, st);  // 0 là tồn tại nên để lại 
            // Execute the query and store the result in the ResultSet
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(userad_ad.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    

//tring h = "admin";
    public boolean check(String username, String pass) {
        boolean h = false;  // Khởi tạo mặc định là false

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE username = ? AND pass = ? and UserType= ?");
            //PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE username = ? AND pass = CONVERT(varchar(32),HASHBYTES('MD5', ?), 2) and usertype= ?");
            ps.setString(1, username);
            ps.setString(2, v.getMd5(pass));
            // ps.setString(2, (pass));
            ps.setString(3, "admin");

            ResultSet rs = ps.executeQuery();

            // Kiểm tra xem có kết quả hay không
            if (rs.next()) {
                h = true;  // Tìm thấy một bản ghi khớp
            }
        } catch (SQLException ex) {
            Logger.getLogger(userad_ad.class.getName()).log(Level.SEVERE, null, ex);

        }

        return h;
    }

//    public void delet(String us) {
//
//        try {
//            PreparedStatement ps = conn.prepareStatement("delete users where username =?");
//            ps.setString(1, us);
//            ps.executeUpdate();
//        } catch (SQLException ex) {
//            Logger.getLogger(userad_ad.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public void delete(String username, int st) {

        try {

            PreparedStatement ps = conn.prepareStatement("UPDATE Users SET UserStatus = ? WHERE Username = ?");
            ps.setInt(1, st); // Đặt giá trị UserStatus mà bạn muốn cập nhật (0 hoặc 1) ở đây 1 đang là xóa
            ps.setString(2, username); // Đặt tên người dùng (username) mà bạn muốn cập nhật
            ps.executeUpdate();
            //   int count = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(userad_ad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//    public orr update(String order_id, orr newInfo) {
//        int count = 0;
//        try {
//
//            PreparedStatement ps = conn.prepareStatement("Update [Order] set order_total=?,order_date=?,order_des=? where  order_id=? ");
//            ps.setInt(1, newInfo.getOrder_total());
//            ps.setDate(2, newInfo.getOrder_date());
//            ps.setString(3, newInfo.getOrder_des());
//            ps.setString(4, order_id);
//            count = ps.executeUpdate();
//        } catch (SQLException ex) {
//            Logger.getLogger(OrderDAOS.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return (count == 0) ? null : newInfo;
//    }
}
