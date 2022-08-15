/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package sample.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import sample.shopping.Cart;
import sample.users.ProductDTO;
import sample.users.UserDAO;
/**
 *
 * @author ASUS
 */
public class AddController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "greenfoods-master/shop.jsp";
    static final Logger LOGGER = Logger.getLogger(AddController.class);
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String productID = request.getParameter("productID");
            UserDAO dao = new UserDAO();
            ProductDTO product = dao.getProduct(productID);
            HttpSession session = request.getSession();
            if (product.getQuantity() > 0) {
                Map<String, Integer> list = (Map<String, Integer>) session.getAttribute("QUANTITY");
                if (list == null) {
                    list = new HashMap<>();
                }
                list.put(product.getProductID().trim(), product.getQuantity());

                session.setAttribute("QUANTITY", list);
                Cart cart = (Cart) session.getAttribute("CART");
                product.setQuantity(1);
                product.setProductID(product.getProductID().trim());
                if (cart == null) {
                    cart = new Cart();
                }
                cart.add(product);
                session.setAttribute("CART", cart);
            }

            url = SUCCESS;
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            response.sendRedirect(url);
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
