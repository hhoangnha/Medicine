/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Daos;

import DB.DBConnection;
import Model.ProductModel;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nguyen Hoang Nha - CE170092
 */
public class ProductDAO {

    Connection conn;

    public ProductDAO() {
        try {
            conn = DBConnection.connect();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ResultSet getAllDeletedList() {
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select ProID, ProName, Description, Price, Quantity, Size, Image, Categories.CateName, Brands.BrandName, Color from Products\n"
                    + "left JOIN Categories on Products.CateID = Categories.CateID\n"
                    + "left JOIN Brands on Products.BrandID = Brands.BrandID where ProStatus = 0");
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ResultSet getAll() {
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select ProID, ProName, Description, Price, Quantity, Size, Image, Categories.CateName, Brands.BrandName, Color from Products\n"
                    + "left JOIN Categories on Products.CateID = Categories.CateID\n"
                    + "left JOIN Brands on Products.BrandID = Brands.BrandID where ProStatus = 1");
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ResultSet getByCategory(int cateID) {
        ResultSet rs = null;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Products LEFT JOIN Brand ON Brand.BrandID = Products.BrandID LEFT JOIN Categories ON Categories.CateID = Products.CateID WHERE Products.CateID=? AND Products.Quantity>0 ");
            ps.setInt(1, cateID);
            rs = ps.executeQuery();
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ResultSet searchProduct(String keyword, int page) {
        int recordsPerPage = 10;
        int start = (page - 1) * recordsPerPage;

        ResultSet rs = null;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Products JOIN Brands ON Brands.BrandID = Products.BrandID LEFT JOIN Categories ON Categories.CateID = Products.CateID WHERE Products.ProName LIKE '%" + keyword + "%' AND ProStatus =1 ORDER BY Products.ProID OFFSET " + start + " ROWS FETCH NEXT " + recordsPerPage + " ROWS ONLY ");
//            ps.setString(1, keyword);
//            ps.setInt(2, start);
//            ps.setInt(3, recordsPerPage);
            rs = ps.executeQuery();
            System.out.println("SELECT * FROM Products JOIN Brand ON Brand.BrandID = Products.BrandID LEFT JOIN Categories ON Categories.CateID = Products.CateID WHERE Products.ProName LIKE '%" + keyword + "%' ORDER BY Products.ProID OFFSET " + start + " ROWS FETCH NEXT " + recordsPerPage + " ROWS ONLY ");
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ProductModel getProduct(int pro_id) {
        ProductModel kh = null;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Products LEFT JOIN Brand ON Brand.BrandID = Products.BrandID LEFT JOIN Categories ON Categories.CateID = Products.CateID WHERE Products.ProID=?");
            ps.setInt(1, pro_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int ProID = rs.getInt("ProID");
                String ProName = rs.getString("ProName");
                String ProDescription = rs.getString("ProDescription");
                String CateID = rs.getString("CateID");
                String BrandID = rs.getString("BrandID");
                String ManuID = rs.getString("ManuID");
                Date ManufactureDate = rs.getDate("ManufactureDate");
                Date ExpirationDate = rs.getDate("ExpirationDate");
                String Element = rs.getString("Element");
                int Quantity = rs.getInt("Quantity");
                String Indication = rs.getString("Indication");
                String Contraindication = rs.getString("Contraindication");
                String Using = rs.getString("Using");
                String MadeIn = rs.getString("MadeIn");
                String ProImage = rs.getString("ProImage");
                kh = new ProductModel(ProID,
                        ProName,
                        ProDescription,
                        CateID,
                        BrandID,
                        ManuID,
                        ManufactureDate,
                        ExpirationDate,
                        Element,
                        Quantity,
                        Indication,
                        Contraindication,
                        Using,
                        MadeIn,
                        ProImage);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kh;
    }

    public ResultSet getProduct2(int pro_id) {
        ResultSet kh = null;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Products LEFT JOIN Brands ON Brands.BrandID = Products.BrandID LEFT JOIN Categories ON Categories.CateID = Products.CateID WHERE Products.ProID=?");
            ps.setInt(1, pro_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kh;
    }

    public ProductModel update(int ProID, ProductModel sp) {
        int count = 0;
//        try {
//            PreparedStatement ps = conn.prepareStatement("UPDATE Products SET ProName=?, Description=?, Price=?, Quantity=?, Size=?, Image=? ,CateID=?, BrandID=?, Color=? WHERE ProID=?");
//            ps.setString(1, sp.getProName());
//            ps.setString(2, sp.getDescription());
//            ps.setInt(3, sp.getPrice());
//            ps.setInt(4, sp.getQuantity());
//            ps.setInt(5, sp.getSize());
//            ps.setString(6, sp.getImage());
//            ps.setInt(7, sp.getCateID());
//            ps.setInt(8, sp.getBrandID());
//            ps.setString(9, sp.getColor());
//            ps.setInt(10, sp.getProID());
//            count = ps.executeUpdate();
//        } catch (SQLException ex) {
//            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return (count == 0) ? null : sp;
    }

    public ProductModel delete(int ProID) {
        int count = 0;
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE Products SET ProStatus=? WHERE ProID=?");
            ps.setInt(1, 0);
            ps.setInt(2, ProID);
            count = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (count == 0) ? null : getProduct(ProID);
    }

    public ProductModel updateStatus(int ProID) {
        int count = 0;
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE Products SET ProStatus=? WHERE ProID=?");
            ps.setInt(1, 1);
            ps.setInt(2, ProID);
            count = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (count == 0) ? null : getProduct(ProID);
    }

    public ProductModel addNew(ProductModel sp) {
        int count = 0;
//        try {
//            PreparedStatement ps = conn.prepareStatement("INSERT INTO Products VALUES(?,?,?,?,?,?,?,?,?,?)");
//            ps.setString(1, sp.getProName());
//            ps.setString(2, sp.getDescription());
//            ps.setInt(3, sp.getPrice());
//            ps.setInt(4, sp.getQuantity());
//            ps.setInt(5, sp.getSize());
//            ps.setString(6, sp.getImage());
//            ps.setInt(7, sp.getCateID());
//            ps.setInt(8, sp.getBrandID());
//            ps.setString(9, sp.getColor());
//            ps.setInt(10, sp.getProStatus());
//            count = ps.executeUpdate();
//        } catch (SQLException ex) {
//            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return (count == 0) ? null : sp;
    }
}
