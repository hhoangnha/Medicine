/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Daos;

import DB.DBConnection;
import Model.ManufacturerModel;
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
 * @author Thai Vinh
 */
public class ManufacturerDAO {
//     Connection conn;

//    public ManufacturerDAO() {
//        try {
//            conn = DBConnection.connect();
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
//
//    public ResultSet getAll() {
//        ResultSet rs = null;
//        try {
//            Statement st = conn.createStatement(); // tạo đối tượng thực thi câu lệnh
//            rs = st.executeQuery("SELECT * FROM Manufacturer");
//            return rs;
//        } catch (SQLException ex) {
//            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<ManufacturerModel> getAllManufacturer() {
        List<ManufacturerModel> list = new ArrayList<>();
        String query = "select * from Manufacturer";
        try {
            conn = new DBConnection().connect();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ManufacturerModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
        } catch (Exception e) {
        }
        return list;
    }
    public ResultSet getAllManufacturerRS() {
        
        try {
            // Create a PreparedStatement with a parameterized query
            String sql = "SELECT * FROM Manufacturer";
            ps = conn.prepareStatement(sql);

            // ps.setInt(1, st);  // 0 là tồn tại nên để lại 
            // Execute the query and store the result in the ResultSet
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(userad_ad.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public void deleteManufacturer(String ManuID) {
        String query = "delete from Manufacturer\n"
                + "where ManuID = ?";
        try {
            conn = new DBConnection().connect();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, ManuID);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void addManufacturer(String ManuName, String MfgLicense, String ManuAddress, String Phone) {
        String query = "INSERT [dbo].[Manufacturer] \n"
                + "([ManuName], [MfgLicense], [ManuAddress], [Phone])\n"
                + "VALUES(?,?,?,?)";
        try {
            conn = new DBConnection().connect();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, ManuName);
            ps.setString(2, MfgLicense);
            ps.setString(3, ManuAddress);
            ps.setString(4, Phone);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void editManufacturer(String ManuID, String ManuName, String MfgLicense, String ManuAddress, String Phone) {
        String query = "UPDATE Manufacturer "
                + "SET [ManuName] = ?, "
                + "[MfgLicense] = ?, "
                + "ManuAddress = ?, "
                + "Phone = ? "
                + "WHERE ManuID = ?";

        try {
            conn = new DBConnection().connect();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, ManuName);
            ps.setString(2, MfgLicense);
            ps.setString(3, ManuAddress);
            ps.setString(4, Phone);
            ps.setString(5, ManuID);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public ManufacturerModel getManuById(String id) {
        String query = "select * from Manufacturer\n"
                + "where ManuID = ?";
        try {
            conn = new DBConnection().connect();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new ManufacturerModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static void main(String[] args) {
        ManufacturerDAO manuDAO = new ManufacturerDAO();
        List<ManufacturerModel> rsManu = manuDAO.getAllManufacturer();
        for (int i = 0; i < rsManu.size(); i++) {
            System.out.println(rsManu.get(i).getManuID());
            System.out.println(rsManu.get(i).getManuName());
        }
    }
}
