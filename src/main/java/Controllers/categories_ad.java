/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import static Controllers.loginController.checkAdmin;
import Daos.cate_ad;
import Model.CategorieModel;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Dell
 */
public class categories_ad extends HttpServlet {

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
            out.println("<title>Servlet categories_ad</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet categories_ad at " + request.getContextPath() + "</h1>");
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
            if (p.endsWith("/categories_ad")) {
                request.getRequestDispatcher("admin-categories-list.jsp").forward(request, response);
            }

            if (p.startsWith("/categories_ad/edit")) {
                String[] s = p.split("/");

                try {
                    int cateid = Integer.parseInt(s[s.length - 1]);
                    session = request.getSession();
                    session.setAttribute("cateid", cateid);
                    request.getRequestDispatcher("/Categories_edit.jsp").forward(request, response);

                    //  request.getRequestDispatcher("/hi.jsp").forward(request, response);
                } catch (Exception ex) {
                    response.sendRedirect("/categories_ad");
                }
            }

            if (p.startsWith("/categories_ad/delete")) {
                String[] s = p.split("/");

                try {
                    int cateid = Integer.parseInt(s[s.length - 1]);
                    cate_ad ct = new cate_ad();
                    ct.delete(cateid, 0);

                    response.sendRedirect("/categories_ad");
                } catch (Exception ex) {
                    response.sendRedirect("/categories_ad");
                }

            }
            if (p.endsWith("/categories_ad/addnew")) {
                request.getRequestDispatcher("/admin-categories-create.jsp").forward(request, response);

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
        int h = 0;
        if (request.getParameter("edit") != null) {
            int cateID = Integer.parseInt(request.getParameter("oid"));
            String catename = request.getParameter("name");
            String des = request.getParameter("des");
            int st = Integer.parseInt(request.getParameter("st"));
            //   CategorieModel cm = new CategorieModel(cateID, catename, des);
            cate_ad ct = new cate_ad();
            h = ct.updateCategory(cateID, catename, des, st);
            response.sendRedirect("/categories_ad");
        }
        if (request.getParameter("add") != null) {
            // int cateID = Integer.parseInt(request.getParameter("oid"));
            String catename = request.getParameter("name");
            String des = request.getParameter("des");
            //  int st = Integer.parseInt(request.getParameter("st"));
            //   CategorieModel cm = new CategorieModel(cateID, catename, des);
            cate_ad ct = new cate_ad();
            h = ct.addNewCategory(catename, des);
            if (h == 0) {
                response.sendRedirect("/addnew");
            } else {
                session.setAttribute("msgSuccess", "Thành công");
                response.sendRedirect("/categories_ad");
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
