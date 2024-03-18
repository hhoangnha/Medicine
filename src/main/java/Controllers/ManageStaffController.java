package Controllers;

import Daos.StaffDAO;
import Model.StaffModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import xuly.md5;

/**
 * @Thai Vinh
 */
public class ManageStaffController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    md5 hash = new md5();

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
            System.out.println(deleteID);
            dao.deleteAccounts(deleteID);
            dao.deleteStaff(deleteID);
            response.sendRedirect("/ManageStaffController");
        } else if (editId != null) {

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

            dao.editStaff(editId, editIdNumber, editIssuedBy, editLicenseDate);
            dao.editAccount(editId, editUsername, hash.getMd5(editPassword).toUpperCase(), editFullname, editEmail, editPhone, editAddress, editBirthday, editGender);

        } else {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String fullname = request.getParameter("fullname");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String birthday = request.getParameter("birthday");
            String gender = request.getParameter("sex");
            String idNumber = request.getParameter("idNumber");
            String issuedBy = request.getParameter("issuedBy");
            String licenseDate = request.getParameter("licenseDate");
            if (dao.isUsernameExists(username)) {
                HttpSession session = request.getSession();
                String exist = "This username have existed aldready!!!";
                request.setAttribute("exist", exist);
                request.getRequestDispatcher("admin-add-staff.jsp").forward(request, response);
            } else {
                dao.addAccount(username, hash.getMd5(password).toUpperCase(), fullname, email, phone, address, birthday, gender);
                dao.addStaff(idNumber, issuedBy, licenseDate);
            }

        }
        response.sendRedirect("/ManageStaffController");
    }
}
