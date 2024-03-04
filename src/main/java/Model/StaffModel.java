/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Date;

/**
 *
 * @author Thai Vinh
 */
public class StaffModel {

    private int staffModelID;
    private int userID;
    private String username;
    private String password;
    private String fullname;
    private String email;
    private String phone;
    private String resetToken;
    private String address;
    private Date birthday;
    private String gender;
    private boolean isAdmin;
    private Date createdAt;
    private String idNumber;
    private String issuedBy;
    private Date licenseDate;

    // Constructors
    public StaffModel() {
    }

public StaffModel(int staffID, int userID, String username, String password, String fullname, String email, String phone, String resetToken, String address, Date birthday, String gender, boolean isAdmin, Date createdAt, String idNumber, String issuedBy, Date licenseDate) {
    this.staffModelID = staffID;
    this.userID = userID;
    this.username = username;
    this.password = password;
    this.fullname = fullname;
    this.email = email;
    this.phone = phone;
    this.resetToken = resetToken;
    this.address = address;
    this.birthday = birthday;
    this.gender = gender;
    this.isAdmin = isAdmin;
    this.createdAt = createdAt;
    this.idNumber = idNumber;
    this.issuedBy = issuedBy;
    this.licenseDate = licenseDate;
}

    public StaffModel(int userID, String username, String password, String fullname, String email, String phone, String resetToken, String address, Date birthday, String gender, boolean isAdmin, Date createdAt, String idNumber, String issuedBy, Date licenseDate) {
    this.userID = userID;
    this.username = username;
    this.password = password;
    this.fullname = fullname;
    this.email = email;
    this.phone = phone;
    this.resetToken = resetToken;
    this.address = address;
    this.birthday = birthday;
    this.gender = gender;
    this.isAdmin = isAdmin;
    this.createdAt = createdAt;
    this.idNumber = idNumber;
    this.issuedBy = issuedBy;
    this.licenseDate = licenseDate;
}


    public int getStaffModelID() {
        return staffModelID;
    }

    public void setStaffModelID(int staffModelID) {
        this.staffModelID = staffModelID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getIssuedBy() {
        return issuedBy;
    }

    public void setIssuedBy(String issuedBy) {
        this.issuedBy = issuedBy;
    }

    public Date getLicenseDate() {
        return licenseDate;
    }

    public void setLicenseDate(Date licenseDate) {
        this.licenseDate = licenseDate;
    }

    @Override
    public String toString() {
        return "StaffModel{" + "staffModelID=" + staffModelID + ", userID=" + userID + ", username=" + username + ", password=" + password + ", fullname=" + fullname + ", email=" + email + ", phone=" + phone + ", resetToken=" + resetToken + ", address=" + address + ", birthday=" + birthday + ", gender=" + gender + ", isAdmin=" + isAdmin + ", createdAt=" + createdAt + ", idNumber=" + idNumber + ", issuedBy=" + issuedBy + ", licenseDate=" + licenseDate + '}';
    }

}
