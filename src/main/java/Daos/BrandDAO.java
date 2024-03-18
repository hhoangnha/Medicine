/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Daos;

import DB.DBConnection;
import Model.BrandModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BrandDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<BrandModel> getAll() {
        List<BrandModel> list = new ArrayList<>();
        String query = "select * from Brand";
        try {
            conn = new DBConnection().connect();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new BrandModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void addBrand(String brandName, String origin) {
        String query = "INSERT Brand \n"
                + "(BrandName, Origin)\n"
                + "VALUES(?,?)";
        try {
            conn = new DBConnection().connect();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, brandName);
            ps.setString(2, origin);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void editBrand(String brandId, String brandName, String origin) {
        String query = "UPDATE Brand "
                + "SET BrandName = ?, "
                + "Origin = ?, "
                + "WHERE BrandID = ?";

        try {
            conn = new DBConnection().connect();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, brandName);
            ps.setString(2, origin);
            ps.setString(3, brandId);

            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteBrand(String brandId) {
        String query = "UPDATE Brand SET BrandStatus= 0 WHERE BrandID=?";
        try {
            conn = new DBConnection().connect();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, brandId);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public boolean isExist(String name) {
        String query = "SELECT COUNT(*) FROM Brand WHERE BrandName = ?";
        try {
            conn = new DBConnection().connect(); // Mở kết nối với SQL
            ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0; // Trả về true nếu username tồn tại, ngược lại trả về false
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public BrandModel getBrandById(String id) {
        String query = "select * from Brand\n"
                + "where BrandID = ?";
        try {
            conn = new DBConnection().connect();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new BrandModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public BrandModel updateStatus(String id) {
        int count = 0;
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE Brand SET BrandStatus=1 WHERE BrandID=?");
//            ps.setInt(1, 1);
            ps.setString(1, id);
            count = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (count == 0) ? null : getBrandById(id);
    }
    
    public List<BrandModel> getAllDeletedList() {
        List<BrandModel> list = new ArrayList<>();
        String query = "SELECT * FROM Brand WHERE BrandStatus = 0";
        try {
            conn = new DBConnection().connect();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new BrandModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public static void main(String[] args) {
        BrandDAO test = new BrandDAO();
        System.out.println(test.getBrandById("1"));

    }
}
