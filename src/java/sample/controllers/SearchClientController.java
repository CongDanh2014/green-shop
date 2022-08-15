/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package sample.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import sample.users.ProductDTO;
import sample.users.UserDAO;

/**
 *
 * @author ASUS
 */
public class SearchClientController extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(AddController.class);
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "greenfoods-master/shop.jsp";
        try {
            int index;
            if (request.getParameter("index") != null) {
                index = Integer.parseInt(request.getParameter("index"));
            } else {
                index = 1;
            }

            UserDAO dao = new UserDAO();
            String search = request.getParameter("search_client");
            if (search == null) {
                search = "";
            }
            List<ProductDTO> list = dao.getListProduct(search);
            if (list != null || !list.isEmpty()) {
                int count = list.size();
                int endPage;
                if (count % 12 == 0) {
                    endPage = count / 12;
                } else {
                    endPage = count / 12 + 1;
                }
                list = dao.getListProduct(search, index);
                request.setAttribute("LIST_A", list);
                request.setAttribute("END_PAGE", endPage);
                
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
