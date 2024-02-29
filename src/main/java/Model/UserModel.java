/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Date;

/**
 *
 * @author Nguyen Hoang Nha - CE170092
 */
public class UserModel {

    private int UserID;
    private String Username;
    private String Password;
    private String Fullname;
    private String Email;
    private String Phone;
    private String ResetToken;
    private String Address;
    private Date Birthday;
    private int Gender;
    private int isAdmin;
    private Date CreatedAt;

    public UserModel() {
    }

    public UserModel(int UserID, String Username, String Password, String Fullname, String Email, String Phone, String ResetToken, String Address, Date Birthday, int Gender, int isAdmin, Date CreatedAt) {
        this.UserID = UserID;
        this.Username = Username;
        this.Password = Password;
        this.Fullname = Fullname;
        this.Email = Email;
        this.Phone = Phone;
        this.ResetToken = ResetToken;
        this.Address = Address;
        this.Birthday = Birthday;
        this.Gender = Gender;
        this.isAdmin = isAdmin;
        this.CreatedAt = CreatedAt;
    }

    public UserModel(String username, String phone, String fullname, String email, String phone0, String gender, Date birthday, String address) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String Fullname) {
        this.Fullname = Fullname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getResetToken() {
        return ResetToken;
    }

    public void setResetToken(String ResetToken) {
        this.ResetToken = ResetToken;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public Date getBirthday() {
        return Birthday;
    }

    public void setBirthday(Date Birthday) {
        this.Birthday = Birthday;
    }

    public int getGender() {
        return Gender;
    }

    public void setGender(int Gender) {
        this.Gender = Gender;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Date getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(Date CreatedAt) {
        this.CreatedAt = CreatedAt;
    }

    
}