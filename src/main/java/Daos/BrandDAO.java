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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author C15TQK
 */
public class BrandDAO {
    Connection conn;

    public BrandDAO() {
        try {
            conn = DBConnection.connect();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BrandDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BrandDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet getAll() {
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from Brand ");
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(BrandDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public BrandModel addNew(BrandModel nh) {
        int count = 0;
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Brand VALUES(?,?)");
            ps.setString(1, nh.getBrandName());
            ps.setInt(2, nh.getBrandStatus());
            count = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BrandDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (count == 0) ? null : nh;
    }

    public BrandModel getBrand(int BrandID) {
        BrandModel nh = null;
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Brands where BrandID=?");
            ps.setInt(1, BrandID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                nh = new BrandModel(rs.getInt("BrandID"), rs.getString("BrandName"),rs.getInt("BrandStatus"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BrandDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nh;
    }

    public BrandModel update(int BrandID, BrandModel nh) {
        int count = 0;
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE Brands SET BrandName=? WHERE BrandID=?");          
            ps.setString(1, nh.getBrandName());
            ps.setInt(2, nh.getBrandID());
            count = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BrandDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (count == 0) ? null : nh;
    }

    public BrandModel delete(int BrandID) {
        int count = 0;
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE Brands SET BrandStatus=? WHERE BrandID=?");
            ps.setInt(1, 0);
            ps.setInt(2, BrandID);
            ps.executeUpdate();
            count = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BrandDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (count == 0) ? null : getBrand(BrandID);
    }

}
