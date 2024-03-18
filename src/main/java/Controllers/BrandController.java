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
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author C15TQK
 */
public class BrandController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BrandDAO dao = new BrandDAO();
        List<BrandModel> listB = dao.getAll();
        List<BrandModel> listBrandRestore = dao.getAllDeletedList();

        request.setAttribute("listB", listB);
        request.getRequestDispatcher("admin-brand-list.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String deleteID = request.getParameter("delete");
        String editId = request.getParameter("edit");
        String restoreID = request.getParameter("restore");
        BrandDAO dao = new BrandDAO();
        if (deleteID != null) {
            dao.deleteBrand(deleteID);
            response.sendRedirect("/BrandController");

        } else if (editId != null) {
            String id = request.getParameter(editId);
            BrandModel brand = dao.getBrandById(editId);

            request.setAttribute("detail", brand);

            request.getRequestDispatcher("admin-brand-edit.jsp").forward(request, response);
        } else {
            processRequest(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String editId = request.getParameter("brandID");
        String editBrandName = request.getParameter("brandName");
        String editOrigin = request.getParameter("origin");
        BrandDAO dao = new BrandDAO();
        System.out.println(editId);
        if (editId != null) {
            BrandModel manu = dao.getBrandById(editId);

            dao.editBrand(editId, editBrandName, editOrigin);

        } else {
            String brandName = request.getParameter("brandName");
            String origin = request.getParameter("origin");
            if (dao.isExist(brandName)) {

                String exist = "This brand have existed aldready!!!";
                session.setAttribute("exist", exist);
                request.getRequestDispatcher("admin-brand-create.jsp").forward(request, response);
            } else {
                dao.addBrand(brandName, origin);

            }
            if (session != null) {
                session.invalidate();
            }

        }

        response.sendRedirect("/BrandController");
    }
}
