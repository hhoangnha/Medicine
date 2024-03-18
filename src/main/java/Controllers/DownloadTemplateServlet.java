/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import Daos.BrandDAO;
import Daos.ManufacturerDAO;
import Daos.ProductDAO;
import Daos.cate_ad;
import Model.BrandModel;
import Model.CategorieModel;
import Model.ManufacturerModel;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
//import org.apache.poi.hssf.util.CellRangeAddressList;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author macbook
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024, // 1MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class DownloadTemplateServlet extends HttpServlet {

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
            out.println("<title>Servlet DownloadTemplateServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DownloadTemplateServlet at " + request.getContextPath() + "</h1>");
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

        if (path.endsWith("/DownloadTemplateServlet/import-status")) {
            request.getRequestDispatcher("/Import-status.jsp").forward(request, response);
        } else {
            cate_ad cateDao = new cate_ad();
            BrandDAO brandDao = new BrandDAO();
            ManufacturerDAO manuDao = new ManufacturerDAO();

            List<CategorieModel> categories = cateDao.getListCateModel();
            List<BrandModel> brands = brandDao.getAll();
            List<ManufacturerModel> manufacturers = manuDao.getAllManufacturer();

            // Tạo một workbook mới (xlsx)
            Workbook workbook = new XSSFWorkbook();

            // Tạo một sheet mới
            Sheet sheet = workbook.createSheet("Product Template");

            // Create header row
            Row headerRow = sheet.createRow(0);
            String[] headers = {"ProCode", "ProName", "ProDescription", "Category", "Brand", "ManuID", "Quantity"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            // Create dropdowns for CateID, BrandID, and ManuID
            createDropdownCate(sheet, 1, categories, 3);
            createDropdownBrand(sheet, 1, brands, 4);
            createDropdownManu(sheet, 1, manufacturers, 5);
            addDataValidationForInteger(sheet, 1, 100);

            // Write workbook to response
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=Product_Template.xlsx");
            try {
                workbook.write(response.getOutputStream());
            } finally {
//            workbook.close();
            }
        }

    }

    private void addDataValidationForInteger(Sheet sheet, int startRow, int endRow) {
        DataValidationHelper dvHelper = sheet.getDataValidationHelper();
        DataValidationConstraint dvConstraint = dvHelper.createIntegerConstraint(DataValidationConstraint.OperatorType.BETWEEN, "0", "999999");
        CellRangeAddressList addressList = new CellRangeAddressList(startRow, endRow, 9, 9); // Quantity column is at index 9
        DataValidation validation = dvHelper.createValidation(dvConstraint, addressList);
        validation.setShowErrorBox(true);
        sheet.addValidationData(validation);
    }

    private void createDropdownCate(Sheet sheet, int rowIndex, List<CategorieModel> options, int columnIndex) {
        Row row = sheet.getRow(rowIndex);
        if (row == null) {
            row = sheet.createRow(rowIndex);
        }
        Cell cell = row.createCell(columnIndex);
        String[] categoryNames = options.stream().map(CategorieModel::toString).toArray(String[]::new);
        DataValidationHelper validationHelper = sheet.getDataValidationHelper();
        DataValidationConstraint constraint = validationHelper.createExplicitListConstraint(categoryNames);
        CellRangeAddressList addressList = new CellRangeAddressList(rowIndex, rowIndex, columnIndex, columnIndex);
        DataValidation validation = validationHelper.createValidation(constraint, addressList);
        sheet.addValidationData(validation);
    }

    private void createDropdownBrand(Sheet sheet, int rowIndex, List<BrandModel> options, int columnIndex) {
        Row row = sheet.getRow(rowIndex);
        if (row == null) {
            row = sheet.createRow(rowIndex);
        }
        Cell cell = row.createCell(columnIndex);
        String[] categoryNames = options.stream().map(BrandModel::toString).toArray(String[]::new);
        DataValidationHelper validationHelper = sheet.getDataValidationHelper();
        DataValidationConstraint constraint = validationHelper.createExplicitListConstraint(categoryNames);
        CellRangeAddressList addressList = new CellRangeAddressList(rowIndex, rowIndex, columnIndex, columnIndex);
        DataValidation validation = validationHelper.createValidation(constraint, addressList);
        sheet.addValidationData(validation);
    }

    private void createDropdownManu(Sheet sheet, int rowIndex, List<ManufacturerModel> options, int columnIndex) {
        Row row = sheet.getRow(rowIndex);
        if (row == null) {
            row = sheet.createRow(rowIndex);
        }
        Cell cell = row.createCell(columnIndex);
        String[] categoryNames = options.stream().map(ManufacturerModel::toString).toArray(String[]::new);
        DataValidationHelper validationHelper = sheet.getDataValidationHelper();
        DataValidationConstraint constraint = validationHelper.createExplicitListConstraint(categoryNames);
        CellRangeAddressList addressList = new CellRangeAddressList(rowIndex, rowIndex, columnIndex, columnIndex);
        DataValidation validation = validationHelper.createValidation(constraint, addressList);
        sheet.addValidationData(validation);
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
        ProductDAO productDAO = new ProductDAO(); // Sửa lại nếu cần thiết
        Part filePart = request.getPart("file"); // Lấy file từ request
        InputStream fileContent = filePart.getInputStream();
        try {
            // Một số biến để lưu trữ dữ liệu từ Excel
            String ProCode, ProName, ProDescription, Category, Brand, ManuID, ManufactureDate, ExpirationDate, Element, Indication, Contraindication, Using, MadeIn, ProImage;
            int Quantity;
            Workbook workbook = new XSSFWorkbook(fileContent); // Tạo workbook từ InputStream
            Sheet sheet = workbook.getSheetAt(0);

            // Lặp qua từng dòng trong sheet (bắt đầu từ dòng thứ 1 vì dòng đầu tiên là tiêu đề)
            Iterator<Row> iterator = sheet.iterator();
            iterator.next(); // Bỏ qua dòng tiêu đề

            List<String> successfulProducts = new ArrayList<>();
            List<String> failedProducts = new ArrayList<>();
            List<String> updateProducts = new ArrayList<>();

            while (iterator.hasNext()) {
                Row currentRow = iterator.next();

                ProCode = currentRow.getCell(0).getStringCellValue();
                if (currentRow.getCell(1) != null) {
                    ProName = currentRow.getCell(1).getStringCellValue();
                } else {
                    ProName = "";
                }

                if (currentRow.getCell(2) != null) {
                    ProDescription = currentRow.getCell(2).getStringCellValue();
                } else {
                    ProDescription = "";
                }
                Category = currentRow.getCell(3).getStringCellValue();
                Brand = currentRow.getCell(4).getStringCellValue();
                ManuID = currentRow.getCell(5).getStringCellValue();

                Cell cell = currentRow.getCell(6);

                try {
                    Quantity = (int) cell.getNumericCellValue();
                } catch (Exception e) {
                    Quantity = Integer.parseInt(cell.getStringCellValue());
                }

                int statusImport = productDAO.addOrUpdateProduct(ProCode, ProName, ProDescription, Integer.parseInt(extractID(Category)), Integer.parseInt(extractID(Brand)), Integer.parseInt(extractID(ManuID)), "", "", "", Quantity, "", "", "", "", "");
                if (statusImport == 1) {
                    // Thành công
                    successfulProducts.add(ProCode + " - " + ProName);
                } else if(statusImport == 2){
                    updateProducts.add(ProCode + " - " + ProName);
                } 
                else if (statusImport == -1) {
                    // Thất bại
                    failedProducts.add(ProCode + " - " + ProName);
                }
            }

            HttpSession session = request.getSession();
            session.setAttribute("successfulProducts", successfulProducts);
            session.setAttribute("failedProducts", failedProducts);
             session.setAttribute("updateProducts", updateProducts);
            response.sendRedirect("/DownloadTemplateServlet/import-status");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String extractID(String value) {
        // Phân chia chuỗi bằng dấu gạch ngang
        String[] parts = value.split("-");
        // Trả về phần tử đầu tiên sau khi loại bỏ khoảng trắng ở đầu và cuối chuỗi
        return parts[0].trim();
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
