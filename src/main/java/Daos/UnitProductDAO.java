/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Daos;

import DB.DBConnection;
import Model.BrandModel;
import Model.CartItem;
import Model.UnitModel;
import Model.UnitProductModel;
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
public class UnitProductDAO {

    Connection conn;

    public UnitProductDAO() {
        try {
            conn = DBConnection.connect();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BrandDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BrandDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<UnitProductModel> getProductUnits(int proId) {
        List<UnitProductModel> unitItems = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM UnitProduct WHERE ProID = ?");
            ps.setInt(1, proId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int unitProID = rs.getInt("UnitProductID");
                int UnitID = rs.getInt("UnitID");
                float Price = rs.getFloat("Price");
                UnitProductModel item = new UnitProductModel();
                item.setUnitProductID(unitProID);
                item.setUnitID(UnitID);
                item.setProID(proId);
                item.setPrice(Price);
                unitItems.add(item);
            }
            System.out.println("arr");
            System.out.println(unitItems);

        } catch (SQLException ex) {
            System.out.println("Failed to get cart items");
            ex.printStackTrace();
        }
        return unitItems;
    }

    public ResultSet getProductUnitsRS() {
        List<UnitProductModel> unitItems = new ArrayList<>();
        ResultSet rs = null;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM UnitProduct");
//            ps.setInt(1, proId);
            rs = ps.executeQuery();
            while (rs.next()) {
                int unitProID = rs.getInt("UnitProductID");
                int UnitID = rs.getInt("UnitID");
                float Price = rs.getFloat("Price");
                UnitProductModel item = new UnitProductModel();
                item.setUnitProductID(unitProID);
                item.setUnitID(UnitID);
//                item.setProID(proId);
                item.setPrice(Price);
                unitItems.add(item);
            }
            System.out.println("arr");
            System.out.println(unitItems);

        } catch (SQLException ex) {
            System.out.println("Failed to get cart items");
            ex.printStackTrace();
        }
        return rs;
    }

    public UnitModel getUnitByID(int UID) {
        UnitModel um = null;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Units WHERE UnitID = ?");
            ps.setInt(1, UID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int uID = rs.getInt("UnitID");
                String unitName = rs.getString("UnitName");
                um = new UnitModel(uID, unitName);
            }
        } catch (SQLException ex) {
            System.out.println("Failed to get cart items");
            ex.printStackTrace();
        }
        return um;
    }

    public ResultSet getAllUnitNameBy() {
        UnitModel um = null;
        ResultSet rs = null;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Units ");
//            ps.setInt(1, UID);
            rs = ps.executeQuery();
            while (rs.next()) {
                int uID = rs.getInt("UnitID");
                String unitName = rs.getString("UnitName");
                um = new UnitModel(uID, unitName);
            }
        } catch (SQLException ex) {
            System.out.println("Failed to get cart items");
            ex.printStackTrace();
        }
        return rs;
    }

    public UnitProductModel getUnitProduct(int ProID, int UID) {
        UnitProductModel um = null;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM UnitProduct WHERE UnitID = ? AND ProID = ?");
            ps.setInt(1, UID);
            ps.setInt(2, ProID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                um = new UnitProductModel(rs.getInt("UnitProductID"), rs.getInt("UnitID"), rs.getInt("ProId"), rs.getInt("Price"));
            }
            System.out.print(ProID);
            System.out.println(UID);
            System.out.println(um);
        } catch (SQLException ex) {
            System.out.println("Failed to get cart items");
            ex.printStackTrace();
        }
        return um;
    }
    public static void main(String[] args) throws SQLException {
        UnitProductDAO call = new UnitProductDAO();
        ResultSet rs = call.getAllUnitNameBy();
        System.out.println(rs);
    }
}
