/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import Daos.UserDAO;
import Model.UserModel;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.security.SecureRandom;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author Nguyen Hoang Nha - CE170092
 */
public class ForgotController extends HttpServlet {

    final String username = "bapvinh171@gmail.com"; // Replace with your email
    final String password = "ijti totw kksr dtuo"; // Replace with your password

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
            out.println("<title>Servlet ForgotController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ForgotController at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        String path = request.getRequestURI();
        if (path.startsWith("/ForgotController/ResetPassword/")) {
            String[] s = path.split("/");
            try {
                String token = s[s.length - 1];
                UserDAO ud = new UserDAO();
                UserModel rs = ud.getProfileByToken(token);

                if (rs != null) {
                    session.setAttribute("khreset", rs);
                    request.getRequestDispatcher("/ForgotPass.jsp").forward(request, response);
                } else {
                    System.out.println("not found");
                    response.sendRedirect("/loginController");
                }

            } catch (Exception ex) {
                response.sendRedirect("/ForgotController");
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
        String mailHost = "smtp.gmail.com"; // Or your mail server host

        String from = "bapvinh171@gmail.com"; // Replace with your email
        Properties mailProperties = System.getProperties();
        mailProperties.setProperty("mail.smtp.host", mailHost);
        mailProperties.setProperty("mail.smtp.auth", "true");
        mailProperties.setProperty("mail.smtp.port", "587"); // Port might change based on your mail provider
        mailProperties.put("mail.smtp.starttls.enable", "true");

        UserDAO ud = new UserDAO();

        if (path.startsWith("/ForgotController/Check")) {
            String email = request.getParameter("email");
            String token = generateToken();
            int rs = ud.updateToken(email, token);
            if (rs != 0) {
                Session mailSession = Session.getInstance(mailProperties, new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
                try {
                    MimeMessage message = new MimeMessage(mailSession);

                    message.setFrom(new InternetAddress(from));

                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

                    message.setSubject("[Medicine] Request password recovery.");

                    // Tạo chuỗi HTML chứa link để khôi phục mật khẩu
                    String resetLink = "http://localhost:8080/ForgotController/ResetPassword/" + token;
                    String emailContent = "This is your recovery path. <a href=\"" + resetLink + "\">Click here to restore</a>";

                    // Đặt nội dung email dưới dạng HTML
                    message.setContent(emailContent, "text/html; charset=utf-8");

                    Transport.send(message);
                 
                } catch (MessagingException mex) {
                    mex.printStackTrace();
                    System.out.println("Lỗi gửi");
                }

            }
            //
            response.sendRedirect("/EmailSuccess.jsp");

        }else{
             HttpSession session = request.getSession();
            String password = request.getParameter("forgotpass");
  
            UserModel user = (UserModel) session.getAttribute("khreset");
         
            int res = ud.updatePass(user.getUsername(), password);
            
            if(res !=0){
                ud.updateToken(user.getEmail(), "");
                session.setAttribute("khreset", null);
                response.sendRedirect("/loginController");
            }
        }
    }

    public static String generateToken() {
        // Độ dài của chuỗi token bạn muốn tạo
        int tokenLength = 20;

        // Dùng SecureRandom để tạo chuỗi ngẫu nhiên
        SecureRandom random = new SecureRandom();
        // Ký tự có thể có trong chuỗi token
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder token = new StringBuilder(tokenLength);

        for (int i = 0; i < tokenLength; i++) {
            // Chọn ngẫu nhiên một ký tự từ danh sách characters
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            // Thêm ký tự này vào chuỗi token
            token.append(randomChar);
        }

        return token.toString();
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
