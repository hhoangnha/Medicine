/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

//import static Controllers.UserHomeController.getCurrentDate;
import Daos.UserDAO;
import Model.UserModel;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Ngo Phuc Vinh - CE170573
 */
public class SigUpController extends HttpServlet {

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
        if (path.endsWith("/SigUpController")) {//yeu cau trang sigup
            request.getRequestDispatcher("/sigup.jsp").forward(request, response);
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

        String path = request.getRequestURI();
        if (request.getParameter("register") != null) {
            String fullname = request.getParameter("fullname");
//            Date birthday = Date.valueOf(request.getParameter("birthday"));
            String email = request.getParameter("email");
            String user = request.getParameter("user");
            String address = request.getParameter("address");
            String pass = request.getParameter("pass");
            String repass = request.getParameter("repass");
            String phone = request.getParameter("phone");
            Date CreatedAt = getCurrentDate();
//            LocalDate dateOfBirth = LocalDate.of(request.getParameter("birthday"));
            if (pass.equals(repass)) {
                UserDAO CDAO = new UserDAO();
                UserModel a = CDAO.checkAccountUser(user);
                UserModel b = CDAO.checkAccountEmail(email);
                UserModel c = CDAO.checkAccountPhone(phone);
                if (a != null) {
                    System.out.println("that bai");
                    request.setAttribute("trung", "Username is duplicated.");
                    session.setAttribute("Fail", "Account sigup fail");
                    request.getRequestDispatcher("/sigup.jsp").forward(request, response);

                } else if (b != null) {
                    System.out.println("that bai");
                    request.setAttribute("trung", "Email is duplicated.");
                    session.setAttribute("Fail", "Account sigup fail");
                    request.getRequestDispatcher("/sigup.jsp").forward(request, response);
                } else if (c != null) {
                    System.out.println("that bai");
                    request.setAttribute("trung", "Phone is duplicated.");
                    session.setAttribute("Fail", "Account sigup fail");
                    request.getRequestDispatcher("/sigup.jsp").forward(request, response);
                } else {
                    System.out.println("thanh cong");
                    try {
                        CDAO.sigup(user, pass, fullname, email, phone, CreatedAt);
                        session.setAttribute("Success", "Account sigup successfully");
                        System.out.println("innnnn" + session.getAttribute("Success"));
                    } catch (Exception e) {
                        System.out.println(e);
                    }

                    response.sendRedirect("/loginController");
                }
            }
        }
    }

    public static boolean isEighteenOrOlder(Date dateOfBirth) {
        LocalDate birthday = dateOfBirth.toLocalDate();
        LocalDate currentDate = LocalDate.now();
        Period ageDifference = Period.between(birthday, currentDate);
        return ageDifference.getYears() >= 18;
    }

    public static Date getCurrentDate() {
        // Lấy ngày và tháng hiện tại
        LocalDate today = LocalDate.now();

        // Định dạng ngày và tháng
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Chuyển đổi LocalDate thành String với định dạng đã cho
        String formattedDate = today.format(formatter);

        try {
            // Chuyển đổi String thành java.util.Date
            java.util.Date utilDate = new SimpleDateFormat("dd/MM/yyyy").parse(formattedDate);

            // Chuyển đổi java.util.Date thành java.sql.Date
            return new java.sql.Date(utilDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
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
