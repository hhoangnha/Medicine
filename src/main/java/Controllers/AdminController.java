/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import static Controllers.loginController.checkAdmin;
import Daos.AdminDao;
import Daos.UserDAO;
import Daos.userad_ad;
import Model.UserModel;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Nguyen Hoang Nha - CE170092
 */
public class AdminController extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
        HttpSession session = request.getSession();

        if (checkAdmin(session)) {
            if (path.startsWith("/AdminController/Users/Delete")) {
                String[] s = path.split("/");
                try {
                    String proid = (s[s.length - 1]);
                    System.out.println("tesst" + proid);
                    userad_ad pr = new userad_ad();
                    pr.delete(proid, 0);
                    session.setAttribute("DeleteSuccess", "Account deleted successfully");
                    response.sendRedirect("/AdminController/Users");
                } catch (Exception ex) {

                }

            } else if (path.startsWith("/AdminController/Users/Restore")) {
                String[] s = path.split("/");
                try {
                    String proid = (s[s.length - 1]);
                    userad_ad pr = new userad_ad();
                    pr.delete(proid, 1);
                    session.setAttribute("RestoreSuccess", "Account restore successful");
                    System.out.println(session.getAttribute("RestoreSuccess"));
                    response.sendRedirect("/AdminController/RestoreUsers");
                } catch (Exception ex) {

                }

            } else if (path.startsWith("/AdminController/Users")) { // danh sách user
                if (path.startsWith("/AdminController/Users/View")) {
                    String[] s = path.split("/");
                    try {
                        String proid = (s[s.length - 1]);
                        UserDAO ud = new UserDAO();
                        UserModel um = ud.getProfile(proid);
                        System.out.println(um);
                        session = request.getSession();
                        session.setAttribute("thongtinkh", um);
                      
                        request.getRequestDispatcher("/admin-user-report.jsp").forward(request, response);
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                } else {
                    request.getRequestDispatcher("/admin-user-list.jsp").forward(request, response);
                }

            } else if (path.startsWith("/AdminController/RestoreUsers")) { // danh sách user
                request.getRequestDispatcher("/admin-user-restore.jsp").forward(request, response);

            } else {
                AdminDao dao = new AdminDao();
                System.out.println(dao.getNumberOfOrder());
                request.setAttribute("orderN", dao.getNumberOfOrder());
                request.setAttribute("orderNew", dao.getNumberOfOrderNew());
                request.setAttribute("orderSuccess", dao.getNumberOfOrderSuccess());
                request.setAttribute("orderCancel", dao.getNumberOfOrderCancel());
                request.setAttribute("cusN", dao.getNumberOfCustomer());
                request.setAttribute("revenueTotal", dao.getRevenue());
                request.getRequestDispatcher("/admin-index.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("/UserHomeController");
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
        processRequest(request, response);
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
