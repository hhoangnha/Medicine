/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Daos;

import DB.DBConnection;
import Model.UserModel;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Mode
 *
 * @author Nguyen Hoang Nha - CE170092
 */
public class UserDAO {

    Connection conn;

    public UserDAO() {
        try {
            conn = DBConnection.connect();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ResultSet getAll() {
        ResultSet rs = null;
        try {
            Statement st = conn.createStatement(); // tạo đối tượng thực thi câu lệnh
            rs = st.executeQuery("SELECT * FROM Accounts");
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public UserModel login(String username, String password) {
        UserModel ac = null;
        try {
            Statement st = conn.createStatement();
            String hasPassword = getMd5(password).toUpperCase();
            PreparedStatement ps = conn.prepareStatement("SELECT *  FROM Accounts WHERE (Username = ? Or Email = ? or Phone = ?) AND Password = ?");
            ps.setString(1, username);
            ps.setString(2, username);
            ps.setString(3, username);
            ps.setString(4, hasPassword);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ac = new UserModel(rs.getInt("UserID"), rs.getString("Username"), rs.getString("Password"), rs.getString("Fullname"), rs.getString("Email"), rs.getString("Phone"), rs.getString("ResetToken"), rs.getString("Address"), rs.getDate("Birthday"), rs.getInt("Gender"), rs.getInt("IsAdmin"), rs.getDate("CreatedAt"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ac;
    }

    public static String getMd5(String input) {
        try {

            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            // of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public UserModel checkAccountUser(String Username) {
        UserModel ac = null;
        try {
            Statement st = conn.createStatement();
//            String hasPassword = getMd5(password).toUpperCase();
            PreparedStatement ps = conn.prepareStatement("SELECT *  FROM Accounts WHERE Username = ?");
            ps.setString(1, Username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ac = new UserModel(rs.getInt("UserID"), rs.getString("Username"), rs.getString("Password"), rs.getString("Fullname"), rs.getString("Email"), rs.getString("Phone"), rs.getString("ResetToken"), rs.getString("Address"), rs.getDate("Birthday"), rs.getInt("Gender"), rs.getInt("IsAdmin"), rs.getDate("CreatedAt"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ac;
    }

    public UserModel checkAccountEmail(String Email) {
        UserModel ac = null;
        try {
            Statement st = conn.createStatement();
//            String hasPassword = getMd5(password).toUpperCase();
            PreparedStatement ps = conn.prepareStatement("SELECT *  FROM Accounts WHERE Email = ?");
            ps.setString(1, Email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ac = new UserModel(rs.getInt("UserID"), rs.getString("Username"), rs.getString("Password"), rs.getString("Fullname"), rs.getString("Email"), rs.getString("Phone"), rs.getString("ResetToken"), rs.getString("Address"), rs.getDate("Birthday"), rs.getInt("Gender"), rs.getInt("IsAdmin"), rs.getDate("CreatedAt"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ac;
    }

    public UserModel checkAccountPhone(String Phone) {
        UserModel ac = null;
        try {
            Statement st = conn.createStatement();
//            String hasPassword = getMd5(password).toUpperCase();
            PreparedStatement ps = conn.prepareStatement("SELECT *  FROM Accounts WHERE Phone = ?");
            ps.setString(1, Phone);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ac = new UserModel(rs.getInt("UserID"), rs.getString("Username"), rs.getString("Password"), rs.getString("Fullname"), rs.getString("Email"), rs.getString("Phone"), rs.getString("ResetToken"), rs.getString("Address"), rs.getDate("Birthday"), rs.getInt("Gender"), rs.getInt("IsAdmin"), rs.getDate("CreatedAt"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ac;
    }

    public int checkAccountPhoneUpdate(String Username, String Phone) {
        UserModel ac = null;
        int count = 0;
        try {
            Statement st = conn.createStatement();
//            String hasPassword = getMd5(password).toUpperCase();
            PreparedStatement ps = conn.prepareStatement("SELECT *  FROM Accounts WHERE UserName= ? or Phone = ?");
            ps.setString(1, Username);
            ps.setString(2, Phone);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
//                ac = new UserModel(rs.getInt("UserID"), rs.getString("Username"), rs.getString("Password"), rs.getString("Fullname"), rs.getString("Email"), rs.getString("Phone"), rs.getString("ResetToken"), rs.getString("Address"), rs.getDate("Birthday"), rs.getInt("Gender"), rs.getInt("IsAdmin"), rs.getDate("CreatedAt"));
                count++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public void sigup(String user, String pass, String fullname, String email, String phone, Date createdat) {
        try {
            Statement st = conn.createStatement();
            String hasPassword = getMd5(pass).toUpperCase();
            PreparedStatement ps = conn.prepareStatement("insert into Accounts(Username, Password, Fullname,Email,Phone,ResetToken, Address, Birthday, Gender, IsAdmin,CreatedAt) values( ?, ?, ?, ?, ?, '', ?, ?, 0, 0, ?)");
            ps.setString(1, user);
            ps.setString(2, hasPassword);
            ps.setString(3, fullname);
            ps.setString(4, email);
            ps.setString(5, phone);
//            ps.setString(6, address);
//            ps.setDate(7, birthday);
            ps.setDate(6, createdat);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public UserModel getProfile(String Username) {
        UserModel ac = null;
        try {
            Statement st = conn.createStatement();
//            String hasPassword = getMd5(password).toUpperCase();
            PreparedStatement ps = conn.prepareStatement("SELECT *  FROM Accounts WHERE Username = ?");
            ps.setString(1, Username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ac = new UserModel(rs.getInt("UserID"), rs.getString("Username"), rs.getString("Password"), rs.getString("Fullname"), rs.getString("Email"), rs.getString("Phone"), rs.getString("ResetToken"), rs.getString("Address"), rs.getDate("Birthday"), rs.getInt("Gender"), rs.getInt("IsAdmin"), rs.getDate("CreatedAt"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ac;
    }

    public UserModel getProfileByToken(String token) {
        UserModel kh = null;
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Accounts where ResetToken=?");
            ps.setString(1, token);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                kh = new UserModel(rs.getInt("UserID"), rs.getString("Username"), rs.getString("Password"), rs.getString("Fullname"), rs.getString("Email"), rs.getString("Phone"), rs.getString("ResetToken"), rs.getString("Address"), rs.getDate("Birthday"), rs.getInt("Gender"), rs.getInt("IsAdmin"), rs.getDate("CreatedAt"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kh;
    }

    public ResultSet getUserType(String Username) {
        ResultSet rs = null;
        try {
//            Statement st = conn.createStatement(); // tạo đối tượng thực thi câu lệnh
            PreparedStatement ps = conn.prepareStatement("select UserType from Users where Username=?");
            ps.setString(1, Username);
            rs = ps.executeQuery();
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public UserModel updateProfile(String Username, UserModel newinfo) {
        int count = 0;
        try {
             PreparedStatement ps = conn.prepareStatement("update Users set Fullname=?, Email=?, Phone=?, UserType=?, Gender=?, Birthday=?, Address=? where Username=?");

            ps.setString(1, newinfo.getFullname());
            ps.setString(2, newinfo.getEmail());
            ps.setString(3, newinfo.getPhone());
            ps.setInt(4, newinfo.getGender());
            ps.setDate(5, newinfo.getBirthday());
            ps.setString(6, newinfo.getAddress());
            ps.setString(7, Username);
            count = ps.executeUpdate();
            System.out.println("update ok");
        } catch (SQLException ex) {
            System.out.println("lỗi update");
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (count == 0) ? null : newinfo;
    }

    public int updatePass(String Username, String Password) {
        int count = 0;
        try {
            String hasPassword = getMd5(Password).toUpperCase();
            PreparedStatement ps = conn.prepareStatement("update Accounts set Password=? where Username=?");
//            ps.setString(1, newinfo.getUsername());
            ps.setString(1, hasPassword);
            ps.setString(2, Username);
            count = ps.executeUpdate();
            System.out.println("update ok");
        } catch (SQLException ex) {
            System.out.println("lỗi update");
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (count == 0) ? 0 : 1;
    }

    public int updateToken(String email, String token) {
        int count = 0;
        try {

            PreparedStatement ps = conn.prepareStatement("update Accounts set ResetToken=? where Email=?");
//            ps.setString(1, newinfo.getUsername());
            ps.setString(2, email);
            ps.setString(1, token);
            count = ps.executeUpdate();
            System.out.println(count);
            System.out.println("update ok");
        } catch (SQLException ex) {
            System.out.println("lỗi update");
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (count == 0) ? 0 : 1;
    }
}
