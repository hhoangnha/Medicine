/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import Daos.ProductDAO;
import Daos.UserDAO;
import static Daos.UserDAO.getMd5;
import Model.ProductModel;
import Model.UserModel;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.ResultSet;

/**
 *
 * @author Nguyen Hoang Nha - CE170092
 */
public class UserHomeController extends HttpServlet {

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
            out.println("<title>Servlet UserHomeController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserHomeController at " + request.getContextPath() + "</h1>");
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

        if (session.getAttribute("cart") != null) {

        } else {
            session.setAttribute("cart", null);
        }

        if (path.startsWith("/UserHomeController/Detail")) {
            // response.sendRedirect("/product-detail.jsp");
            String[] s = path.split("/");
            try {
                int pro_id = Integer.parseInt(s[s.length - 1]);
                ProductDAO cDAO = new ProductDAO();
                ProductModel kh = cDAO.getProduct(pro_id);
                if (kh == null) {
                    System.out.println("no");
                } else {
                    System.out.println(kh);
                    session = request.getSession();
                    session.setAttribute("chitietsanpham", kh);
                    request.getRequestDispatcher("/product-detail.jsp").forward(request, response);
                }
            } catch (Exception ex) {
                System.out.println(ex);

                //    response.sendRedirect("/CustomerController");
            }

        } else if (path.endsWith("/UserHomeController/Products")) {
            request.getRequestDispatcher("/user-products.jsp").forward(request, response);
        } else if (path.endsWith("/UserHomeController/Order")) {
            request.getRequestDispatcher("/user-order.jsp").forward(request, response);
        } else if (path.startsWith("/UserHomeController/Profile")) {
            String[] s = path.split("/");
            try {
                String Username = s[s.length - 1];
                UserDAO cDAO = new UserDAO();
                UserModel info = cDAO.getProfile(Username);
                if (info == null) {
                    System.out.println("no");
                    response.sendRedirect("/UserHomeController");
                } else {
                    System.out.println(info);
                    session = request.getSession();
                    session.setAttribute("gender", info.getGender());
                    session.setAttribute("thongtinkhachhang", info);
                    request.getRequestDispatcher("/profileUser.jsp").forward(request, response);
//                    response.sendRedirect("/editproduct.jsp");
                }
            } catch (Exception ex) {
                response.sendRedirect("/UserHomeController");
            }
        } else if (path.endsWith("/UserHomeController/ChangePass")) {
            request.getRequestDispatcher("/ChangePassWord.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/user-home.jsp").forward(request, response);
        }

//            request.getRequestDispatcher("/user-home.jsp").forward(request, response);
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
        if (request.getParameter("update") != null) {//Nguoi dung nhat nut update
            String username = request.getParameter("username");
            String fullname = request.getParameter("name");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String usertype = request.getParameter("UserType");
            String gender = request.getParameter("gender");
            
            Date birthday = Date.valueOf(request.getParameter("birthday"));
            String address = request.getParameter("address");

//            UserModel newinfo = new UserModel(username, phone, fullname,email , phone, usertype, gender, birthday, address);
            UserDAO cDAO;
            cDAO = new UserDAO();
//            UserModel kh = cDAO.updateProfile(username, newinfo);
//            product kh = cDAO.update(Integer.parseInt(cus_id), newinfo);

            response.sendRedirect("/UserHomeController");
        } else if (request.getParameter("changepass") != null) {
            HttpSession session = request.getSession();
            String user = (String) session.getAttribute("user");
            System.out.println(user);
            String newpass = request.getParameter("newpass");
            System.out.println(newpass);
            System.out.println(checkPass(session, request));
            if (checkPass(session, request)) {
                UserDAO cDao = new UserDAO();
                int rs = cDao.updatePass(user, newpass);
                System.out.println(rs);
                if (rs == 1) {
                    response.sendRedirect("/UserHomeController");
                }
            }
        }

    }

    public static boolean checkPass(HttpSession session, HttpServletRequest request) {
        return getMd5(request.getParameter("oldpass")).toUpperCase().equals((String) session.getAttribute("pass"));
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
