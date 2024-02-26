/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import Daos.UserDAO;
import Model.UserModel;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;

/**
 *
 * @author Ngo Phuc Vinh - CE170573
 */
public class SigUpController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        if (path.endsWith("/SigUpController")) {//yeu cau trang sigup
            request.getRequestDispatcher("/sigup.jsp").forward(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        if (request.getParameter("register") != null) {
            String fullname = request.getParameter("fullname");
            Date birthday = Date.valueOf(request.getParameter("birthday"));
            String email = request.getParameter("email");
            String user = request.getParameter("user");
            String address = request.getParameter("address");
            String pass = request.getParameter("pass");
            String repass = request.getParameter("repass");
            String phone = request.getParameter("phone");

            if (pass.equals(repass)) {
                UserDAO CDAO = new UserDAO();
                UserModel a = CDAO.checkAccountUser(user);
                UserModel b = CDAO.checkAccountEmail(email);
                UserModel c = CDAO.checkAccountPhone(phone);
                if (a != null) {
                    System.out.println("that bai");
                    request.setAttribute("trung", "Username is duplicated.");
                    request.getRequestDispatcher("/sigup.jsp").forward(request, response);

                } else if (b != null) {
                    System.out.println("that bai");
                    request.setAttribute("trung", "Email is duplicated.");
                    request.getRequestDispatcher("/sigup.jsp").forward(request, response);
                } else if (c != null) {
                    System.out.println("that bai");
                    request.setAttribute("trung", "Phone is duplicated.");
                    request.getRequestDispatcher("/sigup.jsp").forward(request, response);
                } else {
                    System.out.println("thanh cong");
                    try {
                        CDAO.sigup(user, pass, fullname, email, phone, address, birthday);
                    } catch (Exception e) {
                        System.out.println(e);
                    }

                    response.sendRedirect("/loginController");
                }
            }
        }
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
