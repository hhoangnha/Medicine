/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Daos;

import DB.DBConnection;
import Model.BrandModel;
import Model.CartItem;
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

   
}
