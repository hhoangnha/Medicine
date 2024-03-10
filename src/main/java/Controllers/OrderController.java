/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import Daos.OrderDAO;
import Model.OrderModel;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

/**
 *
 * @author User
 */
public class OrderController extends HttpServlet {

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
            out.println("<title>Servlet OrderController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderController at " + request.getContextPath() + "</h1>");
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
        String path = request.getRequestURI();
        HttpSession session = (HttpSession) request.getSession();
        if (path.endsWith("/OrderController")) {
            request.getRequestDispatcher("admin-order-list.jsp").forward(request, response);
        } else {
            if (path.startsWith("/OrderController/confirmOrder/")) {
                String[] s = path.split("/");
                try {
                    int OrderID = Integer.parseInt(s[s.length - 1]);
                    OrderDAO o = new OrderDAO();
                    o.updateStatus(OrderID, 2);
                    session.setAttribute("msgSuccess", "Delete successfully!");
                    response.sendRedirect("/OrderController");
                } catch (Exception ex) {
                    response.sendRedirect("/OrderController");
                }
            }
            if (path.startsWith("/OrderController/deleteOrder/")) {
                String[] s = path.split("/");
                try {
                    int OrderID = Integer.parseInt(s[s.length - 1]);
                    OrderDAO o = new OrderDAO();
                    o.updateStatus(OrderID, 4);
                    session.setAttribute("msgSuccess", "Delete successfully!");
                    response.sendRedirect("/OrderController");
                } catch (Exception ex) {
                    response.sendRedirect("/OrderController");
                }
            }
            if (path.startsWith("/OrderController/editOrderStatus/")) {
                String[] s = path.split("/");
                try {
                    int OrderID = Integer.parseInt(s[s.length - 1]);
                    session = request.getSession();
                    session.setAttribute("OrderID", OrderID);
                    request.getRequestDispatcher("/admin-order-edit.jsp").forward(request, response);
                } catch (Exception ex) {
                    response.sendRedirect("/OrderController");
                }
            }
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
        OrderDAO o = new OrderDAO();
        if (request.getParameter("btnDeleteOrder") != null) {
            int OrderID = Integer.parseInt(request.getParameter("OrderID"));
            try {
                o.delete(OrderID);
                response.sendRedirect("/OrderController");
            } catch (Exception e) {
                response.sendRedirect("/OrderController/deleteOrder/" + OrderID);
            }
        }
//        if (request.getParameter("btnUpdateStatusOrder") != null) {
//            int OrderID = Integer.parseInt(request.getParameter("OrderID"));
//            int StaffID = Integer.parseInt(request.getParameter("StaffID"));
//            int CustomerID = Integer.parseInt(request.getParameter("CustomerID"));
//            String OrderDate = request.getParameter("OrderDate");
//            int OrderStatus = Integer.parseInt(request.getParameter("OrderStatus"));
//            int Total = Integer.parseInt(request.getParameter("Total"));
//            OrderModel order = new OrderModel(OrderID, OrderDate, OrderDate, OrderStatus, OrderStatus, OrderDate, OrderDate, OrderDate);
//            OrderModel updateStatusOrder = o.update(UnitID, unit);
//
//            if (updateUnit != null) {
//                response.sendRedirect("/UnitController");
//            } else {
//                response.sendRedirect("/UnitController/updateUnit/" + UnitID);
//            }
//        }
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
