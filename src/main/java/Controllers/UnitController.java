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
import java.util.LinkedList;

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
            if(path.endsWith("/UnitController/RestoreUnit")) {
                request.getRequestDispatcher("/admin-unit-restore.jsp").forward(request, response);
            }
            else if (path.endsWith("/UnitController/AddnewUnit")) {
                request.getRequestDispatcher("/addnewUnit.jsp").forward(request, response);
            } else if (path.startsWith("/UnitController/updateUnit")) {
                String[] s = path.split("/");
                int UnitID = Integer.parseInt(s[s.length - 1]);
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
                    udao.updateStatus(UnitID, 0);
                    session.setAttribute("msgSuccess", "Delete successfully!");
                    response.sendRedirect("/UnitController");
                } catch (Exception ex) {
                    response.sendRedirect("/UnitController");
                }
            }
            if (path.startsWith("/UnitController/UnitRestore")) {
                String[] s = path.split("/");
                try {
                    int UnitID = Integer.parseInt(s[s.length - 1]);
                    UnitDAO o = new UnitDAO();
                    o.updateStatus(UnitID,1);
                    response.sendRedirect("/UnitController/RestoreUnit");
                } catch (Exception ex) {
                    response.sendRedirect("/UnitController/RestoreUnit");
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
            boolean t = true;
            String UnitName = request.getParameter("UnitName");
            LinkedList<String> checkUnitName = udao.getAllUnitName();
            for (String string : checkUnitName) {
                if (string.equalsIgnoreCase(UnitName)) {
                    t = false;
                    break;
                }
            }

            if (t == true) {
                UnitModel unit = new UnitModel(1, UnitName, 1);
                UnitModel newUnit = udao.addnew(unit);
                response.sendRedirect("/UnitController");
            } else if (t == false) {
                HttpSession session = request.getSession();
                session.setAttribute("errorMessage", "Failed to add new unit. Please try again.");
                response.sendRedirect("/UnitController/AddnewUnit");
            }
        }
        if (request.getParameter("btnUpdateUnit") != null) {
            boolean t = true;
            LinkedList<String> checkUnitName = udao.getAllUnitName();
            int UnitID = Integer.parseInt(request.getParameter("UnitID"));
            String UnitName = request.getParameter("UnitName");
            for (String string : checkUnitName) {
                if (string.equalsIgnoreCase(UnitName)) {
                    t = false;
                    break;
                }
            }

            if (t == true) {
                UnitModel unit = new UnitModel(UnitID, UnitName, 1);
                UnitModel updateUnit = udao.update(UnitID, unit);
                response.sendRedirect("/UnitController");
            } else if(t == false){
                HttpSession session = request.getSession();
                session.setAttribute("errorMessage", "Failed to update unit. Please try again.");
                response.sendRedirect("/UnitController/updateUnit/" + UnitID);
            }
        }
        if (request.getParameter("btnDeleteUnit") != null) {
            int UnitID = Integer.parseInt(request.getParameter("UnitID"));
            try {
                udao.updateStatus(UnitID, 0);
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
