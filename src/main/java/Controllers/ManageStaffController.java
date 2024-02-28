package Controllers;

import Daos.StaffDAO;
import Model.StaffModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
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
            response.sendRedirect("/ManageStaffController");
        } else if (editId != null) {
//            String id = request.getParameter(editId);
//            StaffDAO manu = dao.getManuById(editId);
//
//            request.setAttribute("detail", manu);
//
//            request.getRequestDispatcher("admin-manufacturer-edit.jsp").forward(request, response);
        } else {
            // Nếu không phải yêu cầu xóa, tiếp tục xử lý các yêu cầu khác
            processRequest(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String editId = request.getParameter("edit");
//        String editManuName = request.getParameter("manuName");
//        String editManuLicense = request.getParameter("manuLicense");
//        String editAddress = request.getParameter("manuAddress");
//        String editPhone = request.getParameter("phone");

        StaffDAO dao = new StaffDAO();

        if (editId != null) {
//            ManufacturerModel manu = dao.getManuById(editId);
//            dao.editManufacturer(editId, editManuName, editManuLicense, editAddress, editPhone);

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
