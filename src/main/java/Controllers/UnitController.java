/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import Daos.UnitDAO;
import Model.UnitModel;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author User
 */
public class UnitController extends HttpServlet {

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
            out.println("<title>Servlet UnitController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UnitController at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        if (path.endsWith("/UnitController")) {
            request.getRequestDispatcher("unit-manage.jsp").forward(request, response);
        } else {
            if (path.endsWith("/UnitController/AddnewUnit")) {
                request.getRequestDispatcher("/addnewUnit.jsp").forward(request, response);
            } else if (path.startsWith("/UnitController/updateUnit")) {
                String[] s = path.split("/");
                String UnitID = s[s.length - 1];
                UnitDAO udao = new UnitDAO();
                UnitModel unitOld = udao.getUnit(UnitID);
                session.setAttribute("unitOld", unitOld);
                request.getRequestDispatcher("/updateUnit.jsp").forward(request, response);
            }
//            else if (path.startsWith("/UnitController/deleteUnit")) {
//                String[] s = path.split("/");
//                String UnitID = s[s.length - 1];
//                UnitDAO udao = new UnitDAO();
//                UnitModel unitOld = udao.getUnit(UnitID);
//                HttpSession session = (HttpSession) request.getSession();
//                session.setAttribute("unitOld", unitOld);
//                request.getRequestDispatcher("/deleteUnit.jsp").forward(request, response);
//            }
            if (path.startsWith("/UnitController/deleteUnit")) {
                String[] s = path.split("/");
                try {
                    int UnitID = Integer.parseInt(s[s.length - 1]);
                    UnitDAO udao = new UnitDAO();
//                    ct.delete(cateid, 0);
                    udao.delete(UnitID);
                    session.setAttribute("msgSuccess", "Delete successfully!");
                    response.sendRedirect("/UnitController");
                } catch (Exception ex) {
                    response.sendRedirect("/UnitController");
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
        UnitDAO udao = new UnitDAO();
        if (request.getParameter("btnAddUnit") != null) {
            int UnitID = Integer.parseInt(request.getParameter("UnitID"));
            String UnitName = request.getParameter("UnitName");
            UnitModel unit = new UnitModel(UnitID, UnitName);
            UnitModel newUnit = udao.addnew(unit);

            if (newUnit != null) {
                response.sendRedirect("/UnitController");
            } else {
                response.sendRedirect("/UnitController/AddnewUnit");
            }
        }
        if (request.getParameter("btnUpdateUnit") != null) {
            int UnitID = Integer.parseInt(request.getParameter("UnitID"));
            String UnitName = request.getParameter("UnitName");
            UnitModel unit = new UnitModel(UnitID, UnitName);
            UnitModel updateUnit = udao.update(UnitID, unit);

            if (updateUnit != null) {
                response.sendRedirect("/UnitController");
            } else {
                response.sendRedirect("/UnitController/updateUnit/" + UnitID);
            }
        }
        if (request.getParameter("btnDeleteUnit") != null) {
            String UnitID = request.getParameter("UnitID");

            try {
                udao.delete(UnitID);
                response.sendRedirect("/UnitController");
            } catch (Exception e) {
                response.sendRedirect("/UnitController/deleteUnit/" + UnitID);
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
