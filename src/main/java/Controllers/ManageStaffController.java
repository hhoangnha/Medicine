package Controllers;

import Daos.StaffDAO;
import Model.StaffModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @Thai Vinh
 */
public class ManageStaffController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        StaffDAO dao = new StaffDAO();

        try {
            // Gọi phương thức để lấy danh sách nhân viên từ DAO
            List<StaffModel> listS = dao.getStaffList();
            // Đặt danh sách nhân viên vào thuộc tính của request
            request.setAttribute("listS", listS);
            // Chuyển hướng tới trang hiển thị danh sách nhân viên
            request.getRequestDispatcher("admin-staff-list.jsp").forward(request, response);
        } catch (Exception e) {
            // Xử lý ngoại lệ nếu có lỗi xảy ra
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String deleteID = request.getParameter("delete");
        String editId = request.getParameter("edit");
        StaffDAO dao = new StaffDAO();
        if (deleteID != null) {
            dao.deleteStaff(deleteID);
            dao.deleteAccounts(deleteID);
            response.sendRedirect("/ManageStaffController");
        } else if (editId != null) {
            String id = request.getParameter(editId);
            StaffModel detail = dao.getStaffById(editId);
            System.out.println(detail);
            request.setAttribute("detail", detail);

            request.getRequestDispatcher("admin-staff-edit.jsp").forward(request, response);
        } else {
            // Nếu không phải yêu cầu xóa, tiếp tục xử lý các yêu cầu khác
            processRequest(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String editId = request.getParameter("edit");

        StaffDAO dao = new StaffDAO();

        if (editId != null) {
            String editUsername = request.getParameter("username");
            String editPassword = request.getParameter("password");
            String editFullname = request.getParameter("fullname");
            String editEmail = request.getParameter("email");
            String editPhone = request.getParameter("phone");
            String editAddress = request.getParameter("address");
            String editBirthday = request.getParameter("birthday");
            String editGender = request.getParameter("gender");
            String editIdNumber = request.getParameter("idNumber");
            String editIssuedBy = request.getParameter("issuedBy");
            String editLicenseDate = request.getParameter("licenseDate");

            System.out.println(editId);
            System.out.println("Username: " + editUsername);
            System.out.println("Password: " + editPassword);
            System.out.println("Fullname: " + editFullname);
            System.out.println("Email: " + editEmail);
            System.out.println("Phone: " + editPhone);
            System.out.println("Address: " + editAddress);
            System.out.println("Birthday: " + editBirthday);
            System.out.println("Gender: " + editGender);
            System.out.println("ID Number: " + editIdNumber);
            System.out.println("Issued By: " + editIssuedBy);
            System.out.println("License Date: " + editLicenseDate);
            dao.editStaffAndAccount(editId, editUsername, editPassword, editFullname, editEmail, editPhone, editAddress, editBirthday, editGender, editIdNumber, editIssuedBy, editLicenseDate);

        } else {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String fullname = request.getParameter("fullname");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String birthday = request.getParameter("birthday");
            String sex = request.getParameter("sex");
            dao.addStaff(username, password, fullname, email, phone, address, birthday, sex);
        }
        response.sendRedirect("/ManageStaffController");
    }
}
