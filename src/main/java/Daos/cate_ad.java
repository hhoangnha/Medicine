/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Daos;

import DB.DBConnection;
import Model.CategorieModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import xuly.md5;

/**
 *
 * @author Dell
 */
public class cate_ad {

    Connection conn;
    xuly.md5 v = new md5();

    public cate_ad() {
        try {
            conn = DBConnection.connect();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(cate_ad.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(cate_ad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<CategorieModel> getListCateModel() {
        List<CategorieModel> listCategory = new ArrayList<>();
        ResultSet rs = null;
        try {
            // Create a PreparedStatement with a parameterized query
            String sql = "SELECT * FROM Categories";
            PreparedStatement ps = conn.prepareStatement(sql);

            // ps.setInt(1, st);  // 0 là tồn tại nên để lại 
            // Execute the query and store the result in the ResultSet
            rs = ps.executeQuery();
            while(rs.next()){
                listCategory.add(new CategorieModel(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(userad_ad.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCategory;
    }
    
    public ResultSet getAll(){
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM Categories";
            PreparedStatement ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            return rs;
        } catch (Exception e) {
        }
        return rs;
    }
    
    public int checkDuplicate(int cateid, String cateName) throws SQLException{
//        CategorieModel isDuplicate = new CategorieModel();
        int isDuplicate = 0;
            String sql = "select * from Categories where CateID = ? or CateName = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cateid);
            ps.setString(2, cateName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                isDuplicate++;
            }
        return isDuplicate;
    }
//    public int updateCategory(int cateid, String cateName, String cateDescription, int cateStatus) {
    public int updateCategory(int cateid, String cateName, String cateDescription) {
        int h = 0;
        try {
            // Sử dụng PreparedStatement để thực hiện truy vấn UPDATE
//            String sql = "UPDATE Categories SET CateName=?, CateDescription=?, CateStatus=? WHERE CateID=?";
            String sql = "UPDATE Categories SET CateName=?, CateDescription=? WHERE CateID=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            // Đặt các tham số cho câu truy vấn
            ps.setString(1, cateName);
            ps.setString(2, cateDescription);
//            ps.setInt(3, cateStatus);
            ps.setInt(3, cateid);

            // Thực hiện truy vấn UPDATE
            h = ps.executeUpdate();   // nếu trả về 0 update that bai neu tra ve >0 update thanh cong dung đieu kien !=0 
        } catch (SQLException ex) {
            Logger.getLogger(cate_ad.class.getName()).log(Level.SEVERE, null, ex);
        }
        return h;
    }

    public int addNewCategory(String cateName, String cateDescription) {
        try {
            // Sử dụng PreparedStatement để thực hiện truy vấn INSERT
//            String sql = "INSERT INTO Categories ( CateName, CateDescription, CateStatus) VALUES ( ?, ?, ?)";
            String sql = "INSERT INTO Categories ( CateName, CateDescription) VALUES ( ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            // Đặt các tham số cho câu truy vấn
            // ps.setInt(1, id);
            ps.setString(1, cateName);
            ps.setString(2, cateDescription);
//            ps.setInt(3, 1);

            // Thực hiện truy vấn INSERT
            int result = ps.executeUpdate();

            // Trả về giá trị thành công (ví dụ: 1) hoặc thất bại (ví dụ: 0)
            return result > 0 ? 1 : 0;
        } catch (SQLException ex) {
            Logger.getLogger(cate_ad.class.getName()).log(Level.SEVERE, null, ex);
            // Trả về giá trị thất bại trong trường hợp có lỗi
            return 0;
        }
    }

//    public int delete(int cateid, int st) {
        public int delete(int cateid) {
        int result = 0;
        try {
//            String sql = "UPDATE Categories SET CateStatus = ? WHERE CateID = ?";
            String sql = "delete from Categories where CateID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            // Đặt các tham số cho câu truy vấn
//            ps.setInt(1, st);
            ps.setInt(1, cateid);

            result = ps.executeUpdate();

            return result > 0 ? 1 : 0;
        } catch (SQLException ex) {
            Logger.getLogger(cate_ad.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public CategorieModel getCate(int cateid) {
        CategorieModel ct = null;

        try {
            String sql = "SELECT * FROM Categories WHERE CateID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cateid);

            // Thực hiện truy vấn SELECT
            ResultSet rs = ps.executeQuery();

            // Kiểm tra xem có bản ghi nào trả về hay không
            if (rs.next()) {
                // Lấy dữ liệu từ ResultSet và tạo đối tượng CategorieModel
                int id = rs.getInt("CateID");
                String cateName = rs.getString("CateName");
                String cateDescription = rs.getString("CateDescription");
//                int cateStatus = rs.getInt("CateStatus");

                // Tạo đối tượng CategorieModel từ dữ liệu truy vấn
//                ct = new CategorieModel(id, cateName, cateDescription, cateStatus);
                ct = new CategorieModel(id, cateName, cateDescription);
            }
        } catch (SQLException ex) {
            Logger.getLogger(cate_ad.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ct;
    }
    public static void main(String[] args) throws SQLException {
        cate_ad ct = new cate_ad();
         List<CategorieModel> listCategory = ct.getListCateModel();
         for(int i = 0; i < listCategory.size(); i++){
             System.out.println(listCategory.get(i).getCateID());
             System.out.println(listCategory.get(i).getCateName());
             System.out.println(listCategory.get(i).getCateDescription());
         }
        
    }
}
