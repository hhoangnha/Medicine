/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import static Controllers.loginController.checkAdmin;
import Daos.cate_ad;
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
public class AdminCateController extends HttpServlet {

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
            out.println("<title>Servlet AdminCateController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminCateController at " + request.getContextPath() + "</h1>");
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
            if (p.endsWith("/AdminCateController")) {
                request.getRequestDispatcher("/admin-categories-list.jsp").forward(request, response);
            } else if (p.endsWith("/AdminCateController/RestoreCate")) {
//            System.out.println("ok");
                request.getRequestDispatcher("/admin-categories-restore.jsp").forward(request, response);
            }
            session.setAttribute("msgSuccess", null);

            if (p.startsWith("/AdminCateController/Edit")) {
                String[] s = p.split("/");

                try {
                    int cateid = Integer.parseInt(s[s.length - 1]);
                    session = request.getSession();
                    session.setAttribute("cateid", cateid);
                    request.getRequestDispatcher("/admin-categories-edit.jsp").forward(request, response);
                } catch (Exception ex) {
                    response.sendRedirect("/AdminCateController");
                }
            }

            if (p.startsWith("/AdminCateController/Delete")) {
                String[] s = p.split("/");

                try {
                    int cateid = Integer.parseInt(s[s.length - 1]);
                    cate_ad ct = new cate_ad();
                    ct.delete(cateid, 0);
                    session.setAttribute("msgSuccess", "Thành công");
                    response.sendRedirect("/AdminCateController");
                } catch (Exception ex) {
                    response.sendRedirect("/AdminCateController");
                }

            }

            if (p.startsWith("/AdminCateController/Restore")) {
                String[] s = p.split("/");

                try {
                    int cateid = Integer.parseInt(s[s.length - 1]);
                    cate_ad ct = new cate_ad();
                    ct.delete(cateid, 1);
                    session.setAttribute("msgSuccess", "Thành công");
                    response.sendRedirect("/AdminCateController");
                } catch (Exception ex) {
                    response.sendRedirect("/AdminCateController");
                }

            }
            if (p.endsWith("/AdminCateController/Create")) {
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
            int st = 1;
            //   CategorieModel cm = new CategorieModel(cateID, catename, des);
            cate_ad ct = new cate_ad();
            h = ct.updateCategory(cateID, catename, des, st);
            session.setAttribute("msgSuccess", "Thành công");
            response.sendRedirect("/AdminCateController");
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
                response.sendRedirect("/AdminCateController/Create");
            } else {
                session.setAttribute("msgSuccess", "Thành công");
                response.sendRedirect("/AdminCateController");
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
