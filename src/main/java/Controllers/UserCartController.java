/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import Daos.CartDAO;
import Daos.OrderDAO;
import Daos.OrderID_DAOad;
import Daos.ProductDAO;
import Model.CartItem;
import Model.OrderModel;
import Model.ProductModel;
import Model.UserModel;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import static java.lang.System.out;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nguyen Hoang Nha - CE170092
 */
public class UserCartController extends HttpServlet {

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
            out.println("<title>Servlet UserCartController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserCartController at " + request.getContextPath() + "</h1>");
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
        UserModel uM = (UserModel) session.getAttribute("acc");
        CartDAO cd = new CartDAO();
        if (path.startsWith("/UserCartController/AddToCart")) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{ \"success\": true }");
            int quan = Integer.parseInt(request.getParameter("quan"));
            String[] s = path.split("/");
            // response.sendRedirect("/product-detail.jsp");
            int pro_id = Integer.parseInt(s[s.length - 1]);

            cd.addOrUpdateCartItem(uM.getUserID(), pro_id, quan);

        } else if (path.startsWith(
                "/UserCartController/RemoveFromCart")) {
            String[] s = path.split("/");
            int pro_id = Integer.parseInt(s[s.length - 1]);

            cd.removeCartItem(uM.getUserID(), pro_id);
            
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{ \"success\": true }");
        } else if (path.startsWith("/UserCartController/DecreaseQuantity")) {
            int quan = Integer.parseInt(request.getParameter("quan"));
            String[] s = path.split("/");
            int pro_id = Integer.parseInt(s[s.length - 1]);

            cd.increaseOrDecreaseCartItemQuantity(uM.getUserID(), pro_id, quan);

            // Trả về JSON hoặc thông báo khác (tùy thuộc vào logic của bạn)
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{ \"success DecreaseQuantity\": true }");
        } else if (path.startsWith(
                "/UserCartController/IncreaseQuantity")) {
            int quan = Integer.parseInt(request.getParameter("quan"));
            String[] s = path.split("/");
            int pro_id = Integer.parseInt(s[s.length - 1]);

            cd.increaseOrDecreaseCartItemQuantity(uM.getUserID(), pro_id, quan);
            
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{ \"success IncreaseQuantity\": true }");
        } else if (path.startsWith(
                "/UserCartController/CancelOrder")) {
            String[] s = path.split("/");
            int pro_id = Integer.parseInt(s[s.length - 1]);
            OrderID_DAOad od;

            try {
                od = new OrderID_DAOad();
                ResultSet pl = od.getProductOrder(pro_id);
                while (pl.next()) {
                    int r = od.addQuanToProduct(pl.getInt("Quantity"), pl.getInt("ProID"));
                }
                int h = od.update(pro_id, 0);
            } catch (SQLException ex) {
                System.out.println(ex);
                Logger.getLogger(OrderID_ad.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UserCartController.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{ \"success\": true }");
        } else {
            request.getRequestDispatcher("/user-cart.jsp").forward(request, response);
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
        HttpSession session = request.getSession();

        if (path.startsWith("/UserCartController/CreateOrder")) {
            try {

                String username = (String) session.getAttribute("user");
                String name = request.getParameter("name");
                String phone = request.getParameter("phone");
                String address = request.getParameter("address");
                String note = request.getParameter("note");
                Date currentDate = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                String formattedDate = formatter.format(currentDate);

                boolean checkSubmitOrder = false;
                OrderDAO oC = new OrderDAO();
                ProductDAO pd = new ProductDAO();
                List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

                int total = 0;

                if (cart != null) {
                    for (CartItem item : cart) {
//                        ProductModel pmd = pd.getProduct(item.getProductId());
//                        boolean checkItem = oC.checkQuanProduct(item.getProductId(), item.getQuantity());
//                        if (checkItem) {
//                            session.setAttribute("errorCartMsg", null);
//                            checkSubmitOrder = true;
//                            total += item.getQuantity() * pmd.getPrice();
//                            continue;
//                        } else {
//                            ProductModel pdm = pd.getProduct(item.getProductId());
//                            session.setAttribute("errorCartMsg", "Sản phẩm " + pdm.getProName() + " hiện không đủ! số lượng còn lại là " + pdm.getQuantity());
//                            checkSubmitOrder = false;
//                            total = 0;
//                            break;
//                        }
                    }
                }

                if (checkSubmitOrder) {
                    System.out.println("Đơn hàng đang được soạn");
                    int od = oC.createNewOrder(0, formattedDate, username, total, 1, address, phone, note, cart);

                    session.setAttribute("cart", null);
                    if (od != 0) {
                        System.out.println("Tạo đơn thành công");
                        response.sendRedirect("/UserHomeController/Order");
                    }
//                     response.sendRedirect("/UserCartController");
                } else {
                    System.out.println("Số lượng đã vượt quá");
                    response.sendRedirect("/UserCartController");
                }

//
//                if (od != 0) {
//                    System.out.println("đã tạo đơn thành công " + od);
//                    cart = (List<CartItem>) session.getAttribute("cart");
//                    if (cart != null) {
//                        for (CartItem item : cart) {
//                            System.out.println(item.getQuantity());
//                        }
//                    }
//
//                } else {
//                    System.out.println("insert failed");
//                }
            } catch (Exception e) {
                response.sendRedirect("/UserCartController");
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
