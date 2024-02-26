/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import static Controllers.loginController.checkAdmin;
import Daos.BrandDAO;

import Model.BrandModel;

import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;

/**
 *
 * @author C15TQK
 */
public class BrandController extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BrandController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BrandController at " + request.getContextPath() + "</h1>");
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
        if (checkAdmin(session)) {
            if (path.endsWith("/BrandController")) {// Yeu cau trang ds
                request.getRequestDispatcher("/admin-brand-list.jsp").forward(request, response);
            } else {
                if (path.endsWith("/BrandController/Create")) {
                    request.getRequestDispatcher("/admin-brand-create.jsp").forward(request, response);
                } else {
                    if (path.startsWith("/BrandController/Edit/")) {
                        String[] s = path.split("/");
                        try {
                            int brandID = Integer.parseInt(s[s.length - 1]);
                            BrandDAO cDAO = new BrandDAO();
                            BrandModel nh = cDAO.getBrand(brandID);
                            if (nh == null) {
                                response.sendRedirect("/BrandController");
                            } else {

                                session.setAttribute("thongtinhanhieu", nh);
                                request.getRequestDispatcher("/admin-brand-edit.jsp").forward(request, response);
                            }
                        } catch (Exception ex) {
                            response.sendRedirect("/BrandController");
                        }
                    } else {
                        if (path.startsWith("/BrandController/Delete/")) {
                            String[] s = path.split("/");
                            try {
                                int brandID = Integer.parseInt(s[s.length - 1]);
                                BrandDAO cDAO = new BrandDAO();
                                cDAO.delete(brandID);
                                response.sendRedirect("/BrandController");
                            } catch (Exception ex) {
                                response.sendRedirect("/BrandController");
                            }
                        } else {
                            if (path.endsWith("/BrandController/DeletedBrandList")) {
                                request.getRequestDispatcher("/DeletedBrandList.jsp").forward(request, response);
                            }
                        }
                    }
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
        HttpSession session = request.getSession();
        if (request.getParameter("btnBrandAddNew") != null) {// Nguoi dung nhan nut submit de them du lieu moi
            String name = request.getParameter("brandName");
            BrandModel nh = new BrandModel(0, name, 1);
            BrandDAO cDAO = new BrandDAO();
            BrandModel rs = cDAO.addNew(nh);

            if (rs == null) {
                // Them that bai
                response.sendRedirect("/BrandController/Create");
            } else {
                session.setAttribute("msgSuccess", "Thành công");
                response.sendRedirect("/BrandController");
            }
        }

        if (request.getParameter("btnBrandUpdate") != null) {
            int id = Integer.parseInt(request.getParameter("brandID"));
            String name = request.getParameter("brandName");
            BrandModel nh = new BrandModel(id, name, 1);
            BrandDAO cDAO = new BrandDAO();
            BrandModel rs = cDAO.update(id, nh);

            if (rs == null) {// cap nhat that bai
                BrandModel thongtincu = cDAO.getBrand(id);
                session.setAttribute("thongtinnhanhieu", thongtincu);
                response.sendRedirect("/BrandController/Edit/" + id);
            } else {
                session.setAttribute("msgSuccess", "Thành công");
                response.sendRedirect("/BrandController");
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
