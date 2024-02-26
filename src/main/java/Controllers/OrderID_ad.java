/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import static Controllers.loginController.checkAdmin;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import Daos.OrderID_DAOad;
import jakarta.servlet.http.Part;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
public class OrderID_ad extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet OrderID_ad</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderID_ad at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        String p = request.getRequestURI();
        HttpSession session = request.getSession();
        if (checkAdmin(session)) {
        if (p.endsWith("/OrderID_ad")) {
            request.getRequestDispatcher("/admin-order-list.jsp").forward(request, response);
        }
        if (p.startsWith("/OrderID_ad/view")) {
            String[] s = p.split("/");
            try {
                String orderid = (s[s.length - 1]);

                {

                    
                    session.setAttribute("orderid", orderid);
                    request.getRequestDispatcher("/admin-order-edit.jsp").forward(request, response);
                    //     request.getRequestDispatcher("/View_OrderID.jsp").forward(request, response);
                }

            } catch (Exception ex) {
                //   request.getRequestDispatcher("/hi.jsp").forward(request, response);
                response.sendRedirect("/OrderID_ad");
            }
        } else if (p.startsWith("/OrderID_ad/edit")) {
            String[] s = p.split("/");
            try {
                String orderid = (s[s.length - 1]);

                {

                     session = request.getSession();
                    session.setAttribute("orderidd", orderid);

                    request.getRequestDispatcher("/Update_OrderID.jsp").forward(request, response);
                    //     request.getRequestDispatcher("/View_OrderID.jsp").forward(request, response);
                }

            } catch (Exception ex) {
                //  request.getRequestDispatcher("/hi.jsp").forward(request, response);
                response.sendRedirect("/OrderID_ad");
            }
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

        if (request.getParameter("Create") != null) {
            OrderID_DAOad od;
            try {
                od = new OrderID_DAOad();
                int l = Integer.parseInt(request.getParameter("oid"));
                int y = Integer.parseInt(request.getParameter("a"));
                if(y == 0){
                        System.out.println("trạng thái huỷ đơ");
                    ResultSet pl = od.getProductOrder(l);
                    try {
                        while (pl.next()) {
                            int r = od.addQuanToProduct(pl.getInt("Quantity"),pl.getInt("ProID"));
                        }
                    } catch (SQLException ex) {
                        System.out.println(ex);
                        Logger.getLogger(OrderID_ad.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        
                }
                int h = od.update(l, y);
                if (h == 0) {
                    Cookie xx = new Cookie("update", "failed");
                    xx.setMaxAge(72000);
                    response.addCookie(xx);
                   response.sendRedirect("/OrderID_ad");
                } else {
                    response.sendRedirect("/OrderID_ad");
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(OrderID_ad.class.getName()).log(Level.SEVERE, null, ex);
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
