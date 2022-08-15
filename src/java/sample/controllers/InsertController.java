/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package sample.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import sample.users.ProductDTO;
import sample.users.UserDAO;
import sample.users.UserError;

/**
 *
 * @author ASUS
 */
public class InsertController extends HttpServlet {

    private static final String SUCCESS = "admin.jsp";
    private static final String ERROR = "create.jsp";
    static final Logger LOGGER = Logger.getLogger(AddController.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            String productID = request.getParameter("productID");
            String productName = request.getParameter("productName");
            String image = request.getParameter("image");
            float price = Float.parseFloat(request.getParameter("price"));
            int quantity = (int) Double.parseDouble(request.getParameter("quantity"));
            String categoryID = request.getParameter("categoryID");
            String categoryName = request.getParameter("catagoryName");
            Date now = new Date();
            String importDate = f.format(now);
            int usingDateString = (int) Double.parseDouble(request.getParameter("usingDate"));
            now.setDate(now.getDate() + usingDateString);
            String usingDate = f.format(now);
            UserError userError = new UserError();
            UserDAO dao = new UserDAO();
            boolean checkDuplicate = dao.checkDuplicateProduct(productID);
            if (!checkDuplicate) {
                boolean check = dao.createProduct(new ProductDTO(productID, productName, image, price, quantity, categoryID, categoryName, importDate, usingDate));
                if (check) {
                    url = SUCCESS;
                }
            }

        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
