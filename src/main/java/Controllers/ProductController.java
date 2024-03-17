/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import static Controllers.loginController.checkAdmin;
import Daos.ProductDAO;
import Daos.UnitProductDAO;
import Model.ProductModel;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
//import java.sql.Date;
import java.util.Date;
import jakarta.servlet.annotation.MultipartConfig;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author C15TQK
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024, // 1MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class ProductController extends HttpServlet {

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
            out.println("<title>Servlet ProductController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductController at " + request.getContextPath() + "</h1>");
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
            if (path.endsWith("/ProductController")) {// Yeu cau trang ds
                request.getRequestDispatcher("/admin-product-list.jsp").forward(request, response);
            } else {
                if (path.endsWith("/ProductController/Create")) {
                    request.getRequestDispatcher("/admin-product-create.jsp").forward(request, response);
                } else {
                    if (path.startsWith("/ProductController/Edit/")) {
                        String[] s = path.split("/");
                        try {
                            int ProID = Integer.parseInt(s[s.length - 1]);
                            ProductDAO cDAO = new ProductDAO();
                            ProductModel sp = cDAO.getProduct(ProID);
                            if (sp == null) {
                                response.sendRedirect("/ProductController");
                            } else {

                                session.setAttribute("thongtinsanpham", sp);
                                request.getRequestDispatcher("/admin-product-edit.jsp").forward(request, response);
                            }
                        } catch (Exception ex) {
                            response.sendRedirect("/ProductController");
                        }
                    } else {
                        if (path.startsWith("/ProductController/Delete/")) {
                            String[] s = path.split("/");
                            try {
                                int ProID = Integer.parseInt(s[s.length - 1]);
                                ProductDAO cDAO = new ProductDAO();
                                cDAO.delete(ProID);
                                response.sendRedirect("/ProductController");
                            } catch (Exception ex) {
                                response.sendRedirect("/ProductController");
                            }
                        } else {
                            if (path.endsWith("/ProductController/DeletedProductList")) {
                                request.getRequestDispatcher("/DeletedProductList.jsp").forward(request, response);
                            } else {
                                if (path.startsWith("/ProductController/RestoreProduct")) {
                                    request.getRequestDispatcher("/admin-product-restore.jsp").forward(request, response);
                                } else {
                                    if (path.startsWith("/ProductController/Restore/")) {
                                        String[] s = path.split("/");
                                        try {
                                            int ProID = Integer.parseInt(s[s.length - 1]);
                                            ProductDAO cDAO = new ProductDAO();
                                            ProductModel sp = cDAO.updateStatus(ProID);
                                            response.sendRedirect("/ProductController/RestoreProduct");
                                        } catch (Exception ex) {
                                            response.sendRedirect("/ProductController");
                                        }
                                    } else {
                                        if (path.endsWith("/ProductController/ErrorPicture")) {
                                            request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
                                        }
                                    }
                                }
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

        if (request.getParameter("btnAddNew") != null) {// Nguoi dung nhan nut submit de them du lieu moi
            String ProCode = request.getParameter("code");
            String ProName = request.getParameter("name");
            String manufactureDateStr = request.getParameter("manufacturedate");
            String expirationDate = request.getParameter("expirationdate");
            int catid = Integer.parseInt(request.getParameter("catid"));
            int brandid = Integer.parseInt(request.getParameter("brandid"));
            int manuid = Integer.parseInt(request.getParameter("manuid"));
            Part filePart = request.getPart("image");
            String[] selectedUnits = request.getParameterValues("unitid"); // Mảng các đơn vị đã chọn
            String[] prices = request.getParameterValues("prices[]");

            if (selectedUnits != null) {
                for (String unit : selectedUnits) {
                    System.out.println("Selected Unit: " + unit);
                }
            }

            if (prices != null) {
                for (String price : prices) {
                    System.out.println("Price: " + price);
                }
            }

            String des = request.getParameter("des");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String indicaction = request.getParameter("indication");
            String contraindication = request.getParameter("contraindication");
            String using = request.getParameter("using");
            String element = request.getParameter("element");
            String madein = request.getParameter("madein");

            // Lấy tệp ảnh từ request
            // Đường dẫn đến thư mục lưu trữ ảnh trên máy chủ
            String realPart = getServletContext().getRealPath("/resources/images");
            String fileName = extractFileName(filePart);
            File imgDir = new File(realPart);
            // is can't see then Create new Founder img in path f
            if (!imgDir.exists()) {
                imgDir.mkdir();
            }
            // tiếp tục code ghi file
            filePart.write(realPart + "/" + fileName);
            // Lưu tệp ảnh vào thư mục lưu trữ
//             Tạo một đối tượng Product với thông tin và đường dẫn ảnh
            if (!fileName.contains(".jpg") && !fileName.contains(".jpeg") && !fileName.contains(".png") && !fileName.contains(".webp") && !fileName.contains(".gif")) {
                response.sendRedirect("/ProductController/ErrorPicture");
            } else {
//                ProductModel newSP = new ProductModel(0, ProName, des, price, quantity, size, color, newFileName, catid, brandid, 1);
//                ProductModel newSP = new ProductModel;
                // Thêm sản phẩm mới vào cơ sở dữ liệu
                ProductDAO cDAO = new ProductDAO();
                int rs = cDAO.addNew(ProCode, ProName, des, catid, brandid, manuid, manufactureDateStr, expirationDate, element, quantity, indicaction, contraindication, using, madein, fileName);
                if (rs == -1) {
                    System.out.println("Mã Sản phẩm đã tồn tại");
                    response.setContentType("text/html");
                    response.setCharacterEncoding("UTF-8");
                    PrintWriter out = response.getWriter();

                    String message = "Product Code exst. Please go back and change Product code.";
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Add new failed</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Error</h1>");
                    out.println("<p>" + message + "</p>");
                    out.println("<button onclick=\"goBack()\">Back to add</button>");
                    out.println("<script>");
                    out.println("function goBack() {");
                    out.println("  window.history.back();");
                    out.println("}");
                    out.println("</script>");
                    out.println("</body>");
                    out.println("</html>");

//                    // Them that bai
//                    response.sendRedirect("/ProductController/Create");
                } else if (rs == 0) {
                    System.out.println("thêm thất bại");
                } else {
                    UnitProductDAO upd = new UnitProductDAO();
                    for (int i = 0; i < selectedUnits.length; i++) {
                        int unitId = Integer.parseInt(selectedUnits[i]);
                        int price = Integer.parseInt(prices[i]);

                        upd.addNew(unitId, rs, price);
                    }
//                    response.sendRedirect("/ProductController");
                }

            }

        }

        if (request.getParameter(
                "btnUpdate") != null) {
            int id = Integer.parseInt(request.getParameter("id"));
            String ProCode = request.getParameter("code");
            String ProName = request.getParameter("name");
            String currentImage = request.getParameter("currentImage");
            String manufactureDateStr = request.getParameter("manufacturedate");
            String expirationDate = request.getParameter("expirationdate");
            int catid = Integer.parseInt(request.getParameter("catid"));
            int brandid = Integer.parseInt(request.getParameter("brandid"));
            int manuid = Integer.parseInt(request.getParameter("manuid"));
            Part newFilePart = request.getPart("image");
            String newFileName;

            String des = request.getParameter("des");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String indicaction = request.getParameter("indicaction");
            String contraindication = request.getParameter("contraindication");
            String using = request.getParameter("using");
            String element = request.getParameter("element");
            String madein = request.getParameter("madein");

            if (newFilePart != null) {
                newFileName = extractFileName(newFilePart);
            } else {
                newFileName = null;
            }
            if (newFilePart != null) {
                String allowedExtensions = ".jpg,.jpeg,.png,.gif";
                String[] fileParts = newFileName.split("\\.");
                String fileExtension = fileParts[fileParts.length - 1].toLowerCase();

                if (newFilePart != null && allowedExtensions.contains(fileExtension)) {
                    // Đường dẫn đến thư mục lưu trữ ảnh trên máy chủ
                    String realPart = getServletContext().getRealPath("/resources/images");
                    newFileName = extractFileName(newFilePart);
                    File imgDir = new File(realPart);
                    // is can't see then Create new Founder img in path f
                    if (!imgDir.exists()) {
                        imgDir.mkdir();
                    }
                    // tiếp tục code ghi file
                    newFilePart.write(realPart + "/" + newFileName);
                } else {
                    // Tệp không hợp lệ, xử lý lỗi ở đây
                    response.sendRedirect("/errorPage.jsp"); // Chuyển hướng đến trang lỗi
                }
//                String realPart = getServletContext().getRealPath("/resources/images");
//                newFileName = extractFileName(newFilePart);
//                File imgDir = new File(realPart);
//                // is can't see then Create new Founder img in path f
//                if (!imgDir.exists()) {
//                    imgDir.mkdir();
//                }
//                if (newFileName.equals("")) {
//                    newFileName = request.getParameter("oldImage");
//                }
//                // tiếp tục code ghi file
//                newFilePart.write(realPart + "/" + newFileName);

            }
            ProductModel newSP = new ProductModel();
            ProductDAO cDAO = new ProductDAO();
            String fileName = "";
            if (newFilePart != null) {
                fileName = newFileName;
            } else {
                fileName = currentImage;
            }
            int rsUpdate = cDAO.update(ProCode, ProName, des, catid, brandid, manuid, manufactureDateStr, expirationDate, element, quantity, indicaction, contraindication, using, madein, fileName, id);
            if (rsUpdate == 0) {// cap nhat that bai
                ProductModel thongtincu = cDAO.getProduct(id);
                HttpSession session = request.getSession();
                session.setAttribute("thongtinsanpham", thongtincu);
                response.sendRedirect("/ProductController/Edit/" + id);
            } else {
                response.sendRedirect("/ProductController");
            }

        }
    }

    private String extractFileName(Part filePart) {
        String contentDisp = filePart.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
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
