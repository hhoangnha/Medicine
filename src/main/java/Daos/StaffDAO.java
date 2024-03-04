package Daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DB.DBConnection;
import Model.StaffModel;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StaffDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    // Hàm lấy danh sách thông tin staff
    public List<StaffModel> getStaffList() {

        List<StaffModel> staffList = new ArrayList<>();

        String query = "SELECT s.StaffID, a.UserID, a.Username, a.Password, a.Fullname, a.Email, a.Phone, a.ResetToken, "
                + "a.Address, a.Birthday, a.Gender, a.IsAdmin, a.CreatedAt, s.IDNumber, s.IssuedBy, s.LicenseDate "
                + "FROM Staff s INNER JOIN Accounts a ON s.UserID = a.UserID";
        try {
            // Kết nối tới cơ sở dữ liệu
            conn = DBConnection.connect(); // Mở kết nối với cơ sở dữ liệu
            // Tạo PreparedStatement để thực hiện truy vấn
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            // Duyệt kết quả và thêm thông tin vào danh sách
            while (rs.next()) {
                StaffModel staff = new StaffModel();
                staff.setStaffModelID(rs.getInt("StaffID"));
                staff.setUserID(rs.getInt("UserID"));
                staff.setUsername(rs.getString("Username"));
                staff.setPassword(rs.getString("Password"));
                staff.setFullname(rs.getString("Fullname"));
                staff.setEmail(rs.getString("Email"));
                staff.setPhone(rs.getString("Phone"));
                staff.setResetToken(rs.getString("ResetToken"));
                staff.setAddress(rs.getString("Address"));
                staff.setBirthday(rs.getDate("Birthday"));
                staff.setGender(rs.getString("Gender"));
                staff.setIsAdmin(rs.getBoolean("IsAdmin"));
                staff.setCreatedAt(rs.getDate("CreatedAt"));
                staff.setIdNumber(rs.getString("IDNumber"));
                staff.setIssuedBy(rs.getString("IssuedBy"));
                staff.setLicenseDate(rs.getDate("LicenseDate"));
                staffList.add(staff);
            }
        } catch (Exception e) {

        }
        return staffList;
    }

    private String getCurrentDate() {
        // Get current date
        LocalDate currentDate = LocalDate.now();

        // Format date as SQL Date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return currentDate.format(formatter);
    }

    // Method to add a new staff member
    public void addStaff(String username, String password, String fullname, String email, String phone, String address, String birthday, String gender) {
        String query = "INSERT INTO Accounts "
                + "(Username, Password, Fullname, Email, Phone, Address, Birthday, Gender, isAdmin, CreatedAt)"
                + "VALUES(?,?,?,?,?,?,?,?,?,?)";

        String queryStaff = "INSERT INTO Staff "
                + "(UserID, IDNumber, IssuedBy, LicenseDate)"
                + "VALUES(?,?,?,?)";

        // Lấy ra UserID lớn nhất có IsAdmin là 2
        int userID = getMaxAdminUserID();

        try {
            conn = new DBConnection().connect(); // Mở kết nối với cơ sở dữ liệu
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, fullname);
            ps.setString(4, email);
            ps.setString(5, phone);
            ps.setString(6, address);
            ps.setString(7, birthday);
            ps.setString(8, gender);
            ps.setInt(9, 2); // Giá trị mặc định cho trường isAdmin
            ps.setString(10, getCurrentDate()); // Sử dụng phương thức để lấy ngày hiện tại
            ps.executeUpdate();

            // Thêm bản ghi mới vào bảng Staff
            ps = conn.prepareStatement(queryStaff);
            ps.setInt(1, userID);
            // Các giá trị còn lại có thể được set từ tham số hoặc giá trị mặc định tùy thuộc vào yêu cầu của bạn
            // Ví dụ:
            ps.setString(2, "xxxx-xxxx");
            ps.setString(3, "VietNam");
            ps.setString(4, "2024-11-11");
            ps.executeUpdate();

            // Commit transaction sau khi thêm mới thành công vào cả hai bảng
            conn.commit();
            System.out.println("Staff added successfully.");

        } catch (Exception e) {
            System.out.println("Error adding staff: " + e);
        } finally {
            // Đảm bảo đóng kết nối và tài nguyên PreparedStatement sau khi hoàn thành
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error closing resources: " + ex);
            }
        }
    }

    // Hàm lấy ra UserID lớn nhất có IsAdmin là 2
    public int getMaxAdminUserID() {
        int maxUserID = -1;

        // Chuỗi truy vấn SQL
        String query = "SELECT MAX(a.UserID) AS MaxAdminUserID "
                + "FROM Accounts a "
                + "WHERE a.IsAdmin = 2";

        try {
            // Kết nối tới cơ sở dữ liệu
            conn = DBConnection.connect(); // Mở kết nối với cơ sở dữ liệu
            // Tạo PreparedStatement để thực hiện truy vấn
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            // Lấy kết quả của truy vấn
            if (rs.next()) {
                maxUserID = rs.getInt("MaxAdminUserID");
            }
        } catch (Exception e) {
            // Xử lý ngoại lệ
            e.printStackTrace();
        } finally {
            // Đóng tài nguyên ResultSet, PreparedStatement và Connection
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return maxUserID;
    }

    public void deleteStaff(String userID) {
        String deleteStaffQuery = "DELETE FROM Staff WHERE UserID = ?";

        try {
            // Mở kết nối với cơ sở dữ liệu
            conn = new DBConnection().connect();

            // Xóa bản ghi từ bảng Staff
            ps = conn.prepareStatement(deleteStaffQuery);
            ps.setString(1, userID);
            ps.executeUpdate();

            System.out.println("Staff deleted successfully.");
        } catch (Exception e) {
            System.out.println("Error deleting staff: " + e);
        } finally {
            // Đảm bảo đóng kết nối và tài nguyên PreparedStatement sau khi hoàn thành
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error closing resources: " + ex);
            }
        }
    }

    public void deleteAccounts(String userID) {
        String deleteAccountsQuery = "DELETE FROM Accounts WHERE UserID = ?";

        try {
            // Mở kết nối với cơ sở dữ liệu
            conn = new DBConnection().connect();

            // Xóa bản ghi từ bảng Accounts
            ps = conn.prepareStatement(deleteAccountsQuery);
            ps.setString(1, userID);
            ps.executeUpdate();

            System.out.println("Accounts deleted successfully.");
        } catch (Exception e) {
            System.out.println("Error deleting accounts: " + e);
        } finally {
            // Đảm bảo đóng kết nối và tài nguyên PreparedStatement sau khi hoàn thành
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error closing resources: " + ex);
            }
        }
    }

    public StaffModel getStaffById(String id) {
        String query = "SELECT a.UserID, a.Username, a.Password, a.Fullname, a.Email, a.Phone, a.ResetToken, "
                + "a.Address, a.Birthday, a.Gender,a.IsAdmin,a.CreatedAt, s.StaffID, s.IDNumber, s.IssuedBy, s.LicenseDate "
                + "FROM Accounts a JOIN Staff s ON a.UserID = s.UserID "
                + "WHERE s.StaffID = ?";
        try {
            conn = new DBConnection().connect();
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                // Lấy thông tin từ ResultSet và trả về đối tượng StaffModel
                return new StaffModel(rs.getInt("StaffID"), rs.getInt("UserID"), rs.getString("Username"), rs.getString("Password"),
                        rs.getString("Fullname"), rs.getString("Email"), rs.getString("Phone"), rs.getString("ResetToken"),
                        rs.getString("Address"), rs.getDate("Birthday"), rs.getString("Gender"),
                        rs.getBoolean("IsAdmin"), rs.getDate("CreatedAt"), rs.getString("IDNumber"),
                        rs.getString("IssuedBy"), rs.getDate("LicenseDate"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void editStaffAndAccount(String staffId, String username, String password, String fullname, String email, String phone, String address, String birthday, String gender, String idNumber, String issuedBy, String licenseDate) {
        String queryStaff = "UPDATE Staff "
                + "SET IDNumber = ?, "
                + "IssuedBy = ?, "
                + "LicenseDate = ? "
                + "WHERE UserID = ?";

        String queryAccounts = "UPDATE Accounts "
                + "SET Fullname = ?, "
                + "Email = ?, "
                + "Phone = ?, "
                + "Address = ?, "
                + "Birthday = ?, "
                + "Gender = ? "
                + "WHERE UserID = ?";

        try {
            conn = new DBConnection().connect(); // Mở kết nối với SQL

            // Cập nhật thông tin trong bảng Staff
            ps = conn.prepareStatement(queryStaff);
            ps.setString(1, idNumber);
            ps.setString(2, issuedBy);
            ps.setString(3, licenseDate);
            ps.setString(4, staffId);
            ps.executeUpdate();

            // Cập nhật thông tin trong bảng Accounts
            ps = conn.prepareStatement(queryAccounts);
            ps.setString(1, fullname);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, address);
            ps.setString(5, birthday);
            ps.setString(6, gender);
            ps.setString(7, staffId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        // Tạo một đối tượng của lớp StaffManager để sử dụng hàm addStaff()
        StaffDAO staffManager = new StaffDAO();

        StaffDAO test = new StaffDAO();
//        System.out.println(test.getStaffById("1030"));
        test.editStaffAndAccount("1037", "VinhYéuieu", "123", "Ha Thai Vinh", "mail@GAMI.COM", "0982989262", "ADDRESS", "2003-03-03", "1", "123456789", "viet nam", "2003-03-03");

    }

}
