/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Daos;

import DB.DBConnection;
import Model.ProductModel;
import java.sql.Connection;
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
            ResultSet rs = st.executeQuery("select ProID, ProName, Description, Price, Quantity, Size, Image, Categories.CateName, Brand.BrandName, Color from Products\n"
                    + "left JOIN Categories on Products.CateID = Categories.CateID\n"
                    + "left JOIN Brand on Products.BrandID = Brand.BrandID where ProStatus = 0");
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ResultSet getAll() {
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select p.ProID,p.ProCode , p.ProName, c.CateName, b.BrandName, m.ManuName, p.Quantity, p.ProImage from Products p\n"
                    + "join Categories c on c.CateID = p.CateID\n"
                    + "join Brand b on b.BrandID = p.BrandID\n"
                    + "join Manufacturer m on m.ManuID = p.ManuID "
                    + "Order By p.ProID DESC");
            return rs;
        } catch (SQLException ex) {
            System.out.println(ex);
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
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Products JOIN Brand ON Brand.BrandID = Products.BrandID LEFT JOIN Categories ON Categories.CateID = Products.CateID WHERE Products.ProName LIKE '%" + keyword + "%' ORDER BY Products.ProID");
//            ps.setString(1, keyword);
//            ps.setInt(2, start);
//            ps.setInt(3, recordsPerPage);
            rs = ps.executeQuery();
            System.out.println("SELECT * FROM Products JOIN Brand ON Brand.BrandID = Products.BrandID LEFT JOIN Categories ON Categories.CateID = Products.CateID WHERE Products.ProName LIKE '%" + keyword + "%' ORDER BY Products.ProID");
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
                String ProCode = rs.getString("ProCode");
                String ProName = rs.getString("ProName");
                String ProDescription = rs.getString("ProDescription");
                int CateID = rs.getInt("CateID");
                int BrandID = rs.getInt("BrandID");
                int ManuID = rs.getInt("ManuID");
                String ManufactureDate = rs.getString("ManufactureDate");
                String ExpirationDate = rs.getString("ExpirationDate");
                String Element = rs.getString("Element");
                int Quantity = rs.getInt("Quantity");
                String Indication = rs.getString("Indication");
                String Contraindication = rs.getString("Contraindication");
                String Using = rs.getString("Using");
                String MadeIn = rs.getString("MadeIn");
                String ProImage = rs.getString("ProImage");
                kh = new ProductModel(ProID,
                        ProCode,
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

    public ResultSet getProductRS(int pro_id) {
        ResultSet kh = null;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Products LEFT JOIN Brand ON Brand.BrandID = Products.BrandID LEFT JOIN Categories ON Categories.CateID = Products.CateID WHERE Products.ProID=?");
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

    public int update(String ProCode, String ProName, String ProDescription, int CateID, int BrandID, int ManuID, String ManufactureDate, String ExpirationDate, String Element, int Quantity, String Indication, String Contraindication, String Using, String MadeIn, String ProImage, int ProID) {
        int count = 0;
        try {
            PreparedStatement ps = conn.prepareStatement("update Products\n"
                    + " SET ProName = ?, ProDescription = ?, CateID = ?, BrandID = ?, ManuID = ?,\n"
                    + "       ManufactureDate = ?, ExpirationDate = ?, Element = ?,\n"
                    + "       Quantity = ?, Indication = ?, Contraindication = ?, [Using] = ?,\n"
                    + "       MadeIn = ?, ProImage = ?\n"
                    + " where ProID = ?");
            ps.setString(1, ProName);
            ps.setString(2, ProDescription);
            ps.setInt(3, CateID);
            ps.setInt(4, BrandID);
            ps.setInt(5, ManuID);
            ps.setString(6, ManufactureDate);
            ps.setString(7, ExpirationDate);
            ps.setString(8, Element);
            ps.setInt(9, Quantity);
            ps.setString(10, Indication);
            ps.setString(11, Contraindication);
            ps.setString(12, Using);
            ps.setString(13, MadeIn);
            ps.setString(14, ProImage);
            ps.setInt(15, ProID);

            count = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (count == 0) ? 0 : 1;
    }

    public void delete(int ProID) {
//        int count = 0;
        try {
//            PreparedStatement ps = conn.prepareStatement("UPDATE Products SET ProStatus=? WHERE ProID=?");
            PreparedStatement ps = conn.prepareStatement(" DELETE FROM Products WHERE ProID=?");
//            ps.setInt(1, 0);
            ps.setInt(1, ProID);
            ResultSet rs = ps.executeQuery();
            ps = conn.prepareStatement("DBCC CHECKIDENT ('[Products]', RESEED, 0);\n"
                    + "GO");
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
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

//    public ProductModel addNew(ProductModel sp) {
    public int addNew(String ProCode, String ProName, String ProDescription, int CateID, int BrandID, int ManuID, String ManufactureDate, String ExpirationDate, String Element, int Quantity, String Indication, String Contraindication, String Using, String MadeIn, String ProImage) {
        int idNewProduct = 0; // mặc định
        try {
            PreparedStatement psCheck = conn.prepareStatement("SELECT * FROM Products WHERE ProCode = ?");
            psCheck.setString(1, ProCode);
            ResultSet rs = psCheck.executeQuery();
            // nếu tồn tại trả về -1
            if (rs.next()) {
                return -1;
            } else {
                PreparedStatement ps = conn.prepareStatement("INSERT INTO Products(ProCode,ProName,ProDescription,CateID,BrandID,ManuID,ManufactureDate,ExpirationDate,Element,Quantity,Indication,Contraindication,[Using],MadeIn,ProImage ) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, ProCode);
                ps.setString(2, ProName);
                ps.setString(3, ProDescription);
                ps.setInt(4, CateID);
                ps.setInt(5, BrandID);
                ps.setInt(6, ManuID);
                ps.setString(7, ManufactureDate);
                ps.setString(8, ExpirationDate);
                ps.setString(9, Element);
                ps.setInt(10, Quantity);
                ps.setString(11, Indication);
                ps.setString(12, Contraindication);
                ps.setString(13, Using);
                ps.setString(14, MadeIn);
                ps.setString(15, ProImage);

                int count = ps.executeUpdate();

                if (count > 0) {
                    ResultSet generatedKeys = ps.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        idNewProduct = generatedKeys.getInt(1);
                    }
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);

        }
        return idNewProduct;
    }

    public int addOrUpdateProduct(String ProCode, String ProName, String ProDescription, int CateID, int BrandID, int ManuID, String ManufactureDate, String ExpirationDate, String Element, int Quantity, String Indication, String Contraindication, String Using, String MadeIn, String ProImage) {
        int idNewProduct = 0; // Mặc định

        try {
            // Kiểm tra xem sản phẩm đã tồn tại trong hệ thống chưa
            PreparedStatement psCheck = conn.prepareStatement("SELECT * FROM Products WHERE ProCode = ?");
            psCheck.setString(1, ProCode);
            ResultSet rs = psCheck.executeQuery();

            if (rs.next()) {
                // Nếu sản phẩm đã tồn tại, thực hiện cập nhật số lượng của sản phẩm
                int currentQuantity = rs.getInt("Quantity");
                int updatedQuantity = currentQuantity + Quantity;

                PreparedStatement psUpdate = conn.prepareStatement("UPDATE Products SET Quantity = ? WHERE ProCode = ?");
                psUpdate.setInt(1, updatedQuantity);
                psUpdate.setString(2, ProCode);

                int count = psUpdate.executeUpdate();

                if (count > 0) {
                    idNewProduct = rs.getInt("ProID");
                    
                    return 2;// trả về 2 nếu cập nhật
                }
                
            } else {
                // Nếu sản phẩm chưa tồn tại, thực hiện thêm mới sản phẩm
                PreparedStatement ps = conn.prepareStatement("INSERT INTO Products(ProCode,ProName,ProDescription,CateID,BrandID,ManuID,Element,Quantity,Indication,Contraindication,[Using],MadeIn,ProImage) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, ProCode);
                ps.setString(2, ProName);
                ps.setString(3, ProDescription);
                ps.setInt(4, CateID);
                ps.setInt(5, BrandID);
                ps.setInt(6, ManuID);
//                ps.setString(7, ManufactureDate);
//                ps.setString(8, ExpirationDate);
                ps.setString(7, Element);
                ps.setInt(8, Quantity);
                ps.setString(9, Indication);
                ps.setString(10, Contraindication);
                ps.setString(11, Using);
                ps.setString(12, MadeIn);
                ps.setString(13, ProImage);

                int count = ps.executeUpdate();

//                if (count > 0) {
//                    ResultSet generatedKeys = ps.getGeneratedKeys();
//                    if (generatedKeys.next()) {
//                        idNewProduct = generatedKeys.getInt(1);
//                    }
//                }
return 1; // trả về 1 nếu thành công
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
            return -1; //-1 nếu thất bại
        }

        return idNewProduct;
    }

    public static void main(String[] args) {
        ProductDAO call = new ProductDAO();
//        int rs = call.addNew("bvd", "bfd", 1, 1, 1, "2024-01-01", "2026-01-01", "asd", 50, "asd", "asd", "asd", "asd", "asd.png");
//        int rs = call.addNew("DCYT003","bvd", "bfd", 1, 1, 1, "2024-01-01", "2026-01-01", "asd", 50, "indication", "asd", "asd", "asd", "asd.png");
//        System.out.println(rs);

    }
}
