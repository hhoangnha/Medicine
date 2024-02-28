/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Daos;

import DB.DBConnection;
import Model.UnitModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
            rs = st.executeQuery("SELECT * FROM Units");
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(UnitDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
    public void delete(String UnitIdDelete){
        try {
            PreparedStatement ps = conn.prepareStatement("Delete from Units where UnitID = ?");
            ps.setString(1, UnitIdDelete);
            int count = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UnitDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public UnitModel addnew(UnitModel obj){
        int count = 0;
        try {
            PreparedStatement ps = conn.prepareStatement("Insert into Units(UnitName) VALUES(?)");
            ps.setString(1, obj.getUnitName());
            count = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
       return (count == 0) ? null : obj;
    }
    public UnitModel getUnit(String UnitID) {
        UnitModel unit = null;
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Units where UnitID = ?");
            ps.setString(1, UnitID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                unit = new UnitModel(rs.getInt("UnitID"), rs.getString("UnitName"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UnitDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return unit;
    }
}
