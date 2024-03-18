/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Daos;

import DB.DBConnection;
import Model.ManufacturerModel;
import Model.UnitModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class UnitDAO {

    Connection conn;

    public UnitDAO() {
        try {
            conn = DBConnection.connect();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UnitDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UnitDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet getAll() {
        ResultSet rs = null;
        try {
            Statement st = conn.createStatement(); // tạo đối tượng thực thi câu lệnh
            rs = st.executeQuery("SELECT * FROM Units where UnitStatus = 1");
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(UnitDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ResultSet getAllUnitDeleted() {
        ResultSet rs = null;
        try {
            Statement st = conn.createStatement(); // tạo đối tượng thực thi câu lệnh
            rs = st.executeQuery("SELECT * FROM Units where UnitStatus = 0");
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(UnitDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<UnitModel> getAllUnit() {
        List<UnitModel> UnitItems = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Units where UnitStatus = 1");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UnitModel UnitItem = new UnitModel();
                UnitItem.setUnitID(rs.getInt("UnitID"));
                UnitItem.setUnitName(rs.getString("UnitName"));
                UnitItems.add(UnitItem);
            }

            System.out.println(UnitItems.size());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return UnitItems;
    }

    public UnitModel update(int UnitID, UnitModel sp) {
        int count = 0;
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE Units SET UnitName=? WHERE UnitID = ?");
            ps.setString(1, sp.getUnitName());
            ps.setInt(2, sp.getUnitID());
            count = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UnitDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (count == 0) ? null : sp;
    }

//    public void delete(int UnitId) {
//        try {
//            PreparedStatement ps = conn.prepareStatement("Delete from Units where UnitID = ?");
//            ps.setInt(1, UnitId);
//            int count = ps.executeUpdate();
//        } catch (SQLException ex) {
//            Logger.getLogger(UnitDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
    public int updateStatus(int UnitID, int UnitStatus) {
        int count = 0;
        try {
            PreparedStatement ps = conn.prepareStatement("update Units set UnitStatus=? where UnitID=?");
//            ps.setString(1, newinfo.getUsername());
            ps.setInt(1, UnitStatus);
            ps.setInt(2, UnitID);
            count = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (count == 0) ? 0 : 1;
    }
    
    public UnitModel addnew(UnitModel obj) {
        int count = 0;
        try {
            PreparedStatement ps = conn.prepareStatement("Insert into Units(UnitName, UnitStatus) VALUES(?,1)");
            ps.setString(1, obj.getUnitName());
            count = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return (count == 0) ? null : obj;
    }

    public UnitModel getUnit(int UnitID) {
        UnitModel unit = null;
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Units where UnitID = ?");
            ps.setInt(1, UnitID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                unit = new UnitModel(rs.getInt("UnitID"), rs.getString("UnitName"), rs.getInt("UnitStatus"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UnitDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return unit;
    }

    public LinkedList<String> getAllUnitName() {
        LinkedList<String> Unit = new LinkedList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT UnitName from Units");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String UnitName = rs.getString("UnitName");
                Unit.add(UnitName);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Unit;
    }

}
