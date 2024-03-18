/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import Daos.UserDAO;
import Model.UserModel;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

/**
 *
 * @author Nguyen Hoang Nha - CE170092
 */
public class loginController extends HttpServlet {

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
            out.println("<title>Servlet loginController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet loginController at " + request.getContextPath() + "</h1>");
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
        if (path.endsWith("/loginController")) {//yeu cau trang login
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            if (path.endsWith("/Logout")) {
                Cookie cookieUsername = new Cookie("user", "");
                cookieUsername.setMaxAge(0);
                cookieUsername.setPath("/");
                Cookie cookieFullname = new Cookie("fullname", "");
                cookieFullname.setMaxAge(0);
                cookieFullname.setPath("/");
                Cookie cookieUserType = new Cookie("UserType", "");
                cookieFullname.setMaxAge(0);
                cookieFullname.setPath("/");
                response.addCookie(cookieUsername);
                response.addCookie(cookieFullname);
                response.addCookie(cookieUserType);
                HttpSession session = request.getSession();
                session.invalidate();

                response.sendRedirect("/UserHomeController");
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
        String path = request.getRequestURI();
        if (path.endsWith("/loginController")) {
            String username = request.getParameter("user");
            String password = request.getParameter("pass");
//            System.out.println(password);
            UserDAO cDAO = new UserDAO();
            UserModel rs = cDAO.login(username, password);
            if (rs == null) { //fail
                request.setAttribute("mess", "Wrong username or password.");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("acc", rs);
                session.setAttribute("pass", rs.getPassword());
                session.setAttribute("IsAdmin", rs.getIsAdmin());
                session.setAttribute("fullname", rs.getFullname());
                session.setAttribute("gender", rs.getGender());
                session.setAttribute("user", rs.getUsername());
                session.setAttribute("add", rs.getAddress());
                session.setAttribute("email", rs.getEmail());
                session.setAttribute("phone", rs.getPhone());
                session.setAttribute("birthday", rs.getBirthday());
                Cookie cookieUsername = new Cookie("user", rs.getUsername());
                cookieUsername.setMaxAge(60 * 60 * 24 * 3);
                Cookie cookieFullname = new Cookie("fullname", URLEncoder.encode(rs.getFullname()));
                cookieFullname.setMaxAge(60 * 60 * 24 * 3);
//                Cookie cookieUserType = new Cookie("", rs.getUserType());
                cookieUsername.setMaxAge(60 * 60 * 24 * 3);
                response.addCookie(cookieUsername);
                response.addCookie(cookieFullname);

                System.out.println(rs.getIsAdmin());
                if (rs.getIsAdmin() == 1 || rs.getIsAdmin() == 2) {
                    System.out.println("Đang chuyển hướng sang admin");
                    response.sendRedirect("/AdminController");
                } else {
                    
                    response.sendRedirect("/UserHomeController");
                }

            }
        }
    }

    public static boolean checkAdmin(HttpSession session) {
        try {
            System.out.println("Check is admin" );
            System.out.println("line148:"+session.getAttribute("IsAdmin"));
            System.out.println((int)session.getAttribute("IsAdmin") == 1 || (int)session.getAttribute("IsAdmin") == 2);
            return (int)session.getAttribute("IsAdmin") == 1 || (int)session.getAttribute("IsAdmin") == 2;
        } catch (Exception e) {
            return false;
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
