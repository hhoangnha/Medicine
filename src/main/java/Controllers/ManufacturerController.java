/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import static Controllers.loginController.checkAdmin;
import Daos.BrandDAO;
import Daos.ManufacturerDAO;
import Daos.UserDAO;
import Daos.userad_ad;

import Model.BrandModel;
import Model.ManufacturerModel;
import Model.UserModel;

import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.util.List;

/**
 *
 * @author C15TQK
 */
public class ManufacturerController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ManufacturerDAO dao = new ManufacturerDAO();
        List<ManufacturerModel> listM = dao.getAllManufacturer();

        request.setAttribute("listM", listM);
        request.getRequestDispatcher("admin-manufacturer-list.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String deleteID = request.getParameter("delete");
        String editId = request.getParameter("edit");
        ManufacturerDAO dao = new ManufacturerDAO();
        System.out.println(deleteID);
        if (deleteID != null) {
            dao.deleteManufacturer(deleteID);
            response.sendRedirect("/ManufacturerController");
        } else if (editId != null) {
            String id = request.getParameter(editId);
            ManufacturerModel manu = dao.getManuById(editId);

            request.setAttribute("detail", manu);

            request.getRequestDispatcher("admin-manufacturer-edit.jsp").forward(request, response);
        } else {
            // Nếu không phải yêu cầu xóa, tiếp tục xử lý các yêu cầu khác
            processRequest(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String editId = request.getParameter("edit");
        String editManuName = request.getParameter("manuName");
        String editManuLicense = request.getParameter("manuLicense");
        String editAddress = request.getParameter("manuAddress");
        String editPhone = request.getParameter("phone");

        ManufacturerDAO dao = new ManufacturerDAO();

        if (editId != null) {
            ManufacturerModel manu = dao.getManuById(editId);
            dao.editManufacturer(editId, editManuName, editManuLicense,editAddress, editPhone );

        } else {
            String manuName = request.getParameter("manuName");
            String manuLicense = request.getParameter("manuLicense");
            String manuAddress = request.getParameter("manuAddress");
            String manuPhone = request.getParameter("manuPhone");
            dao.addManufacturer(manuName, manuLicense, manuAddress, manuPhone);
        }
        response.sendRedirect("/ManufacturerController");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
